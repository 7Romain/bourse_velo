console.log("ici");
function formatTel(num) {
    return num.replace(/(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/, "$1 $2 $3 $4 $5");
}
const formulairePersonne = document.getElementById("formulairePersonne");
const inputTel = document.getElementById("telephoneInput");
inputTel.addEventListener("blur", function (e) {
    console.log(inputTel.value);
    inputTel.value = formatTel(inputTel.value);
    console.log(inputTel.value);
});

// formulairePersonne.addEventListener("submit", function (e) {
//     console.log(inputTel.value);
//     inputTel.value = formatTel(inputTel.value);
//     console.log(inputTel.value);
// });
