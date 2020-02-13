$(document).ready(function () {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/users",
        dataType: 'json',
        success: function (result) {
            console.log("SUCCESS: ", result);
            var html;
            $.each(result.data, function () {
                html = `<tr class="text-mx-center tr-${this.id}">
                    <th> ${this.id} </th>
                    <td> ${this.login} </td>
                    <td> ${this.firstName} </td>
                    <td> ${this.lastName} </td>
                    <td> ${this.birthday} </td>
                    <td>
                    <div class="buttons-group">
                        <button id="modal_edit_btn" type="button" class="btn btn-info" data-toggle="modal" user-id="${this.id}">Edit</button>
                        <span>
                            <button id="delete_user_btn" type="button" class="btn btn-info" user-id="${this.id}">Delete</button>
                        </span>
                    </div>
                    </td>
                    </tr>`;
                $('#usersTable').append(html);
            })
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
});

$(document).on('click', '#delete_user_btn', function () {
    var data = {};
    data['id'] = $(this).attr('user-id');
    $.ajax({
        type: "POST",
        url: '/admin/delete/',
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: 'json',
        success: (e) => {
            $('.tr-' + e.data).remove()
        },
        error: (e) => {
            console.log(e, "some error")
        }
    })
});

$(document).on('click', '#modal_edit_btn', function (e) {
    var data = {};
    data['id'] = $(this).attr('user-id');
    $.ajax({
        type: "GET",
        url: '/admin/user',
        data: {id: $(this).attr('user-id')},
        contentType: "application/json",
        dataType: 'json',
        success: (result) => {
            console.log('in function ajaxGetUser');
            var modal = $('#modal_edit');
            var user = result.data;
            $('.modal-title').html('<h5 class="modal-title" id="modalLabel">Edit user - ' + user.login + '</h5>');
            $('#idModal').val(user.id);
            $('#loginModal').val(user.login);
            $('#firstNameModal').val(user.firstName);
            $('#lastNameModal').val(user.lastName);
            $('#birthdayModal').val(user.birthday);
            $('#passwordModal').val(user.password);

            $('#roles').children().remove();
            $('#checkbox').prop('checked', false);
            $.each($(user.roles), function (index, value) {
                $('#roles').append('<role class ="role"><input name="role_id" value="'+ value.id +'">' +
                    '<input name="role_name" value="'+ value.name+'"></role>');
                if(value.name == 'ROLE_ADMIN'){
                    $('#checkbox').prop('checked', true);
                }
            });
            modal.modal('show');
        },
        error: (e) => console.log(e, "some error")
    });
});

$('#addUserForm').on('submit', function (e) {
    e.preventDefault();
    var data = {};
    data['login'] = $('#loginAdd').val();
    data['firstName'] = $('#firstNameAdd').val();
    data['lastName'] = $('#lastNameAdd').val();
    data['birthday'] = $('#birthdayAdd').val();
    data['password'] = $('#passwordAdd').val();

    $.ajax({
        type: 'POST',
        url: '/admin/add',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: "application/json",
        success: (e) => {
            location.reload();
        },
        error: (e) => {
            console.log(e)
        }
    })
});

$('#editUserForm').on('submit', function (e) {
    e.preventDefault();
    var data = {};
    var roles = [];
    var role = {};
    $('.role').children().each(function (index) {
        if(this.name == 'role_id'){
            role['id'] = $(this).val();
        } else {
            role['name'] = $(this).val();
            roles.push(role);
            role = {};
        }
    });
    console.log(roles);
    data['id'] = $('#idModal').val();
    data['login'] = $('#loginModal').val();
    data['firstName'] = $('#firstNameModal').val();
    data['lastName'] = $('#lastNameModal').val();
    data['birthday'] = $('#birthdayModal').val();
    data['password'] = $('#passwordModal').val();
    data['roles'] = roles;
    data['isAdmin'] = $('#checkbox').is(':checked')?'1':'0';
    $.ajax({
        type: 'POST',
        url: '/admin/edit',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: "application/json",
        success: (e) => {location.reload()},
        error: (e) => console.log(e, "some error")
    })
});
