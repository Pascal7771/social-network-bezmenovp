const saveChangeSubmit = document.querySelector('#saveChange');
const deleteGroupSubmit = document.querySelector('#deleteGroup');

saveChangeSubmit.addEventListener("click", function (a) {
    if (!confirm("Save change?")) {
        a.preventDefault();
    }
})

deleteGroupSubmit.addEventListener("click", function (a) {
    if (!confirm("Delete account?")) {
        a.preventDefault();
    }
})