const testPhoneSubmit = document.querySelector('#testPhone');
const saveChangeSubmit = document.querySelector('#saveChange');
const testWorkPhoneSubmit = document.querySelector('#testWorkPhone');
const delWorkPhoneSubmit = document.querySelector('#delWorkPhone');
const regPhone = /^(([0-9]){10})$/;
const inputPhone = document.getElementById("phone");
const inputWorkPhone = document.getElementById("workPhone");
const deleteAccount = document.querySelector('#deleteAccount');

function validatePhone(phone) {
    return !(!regPhone.test(phone));
}

testPhoneSubmit.addEventListener("click", function () {
    if (!validatePhone(inputPhone.value)) {
        inputPhone.style.border = '2px solid red';
        alert("Invalid number format");
        return true;
    } else {
        inputPhone.style.border = '2px solid green';
        return false;
    }
});

testWorkPhoneSubmit.addEventListener("click", function () {
    if (!validatePhone(inputWorkPhone.value) && inputWorkPhone.value !== "") {
        inputWorkPhone.style.border = '2px solid red';
        alert("Invalid number format");
        return true;
    } else {
        inputWorkPhone.style.border = '2px solid green';
        return false;
    }
});

saveChangeSubmit.addEventListener("click", function (a) {
    if (!confirm("Save change?")) {
        a.preventDefault();
    }
})

deleteAccount.addEventListener("click", function (a) {
    if (!confirm("Delete account?")) {
        a.preventDefault();
    }
})

delWorkPhoneSubmit.addEventListener("click", function (a) {
    document.getElementById("workPhone").value = "";
})