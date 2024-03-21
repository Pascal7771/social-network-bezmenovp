$(function () {
    $("#accordion").accordion({
        collapsible: true
    });
});

const testInvite = document.querySelector('#invite');
testInvite.addEventListener("click", function (a) {
    alert("Successfully")
})