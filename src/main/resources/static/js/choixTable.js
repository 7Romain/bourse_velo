// console.log("le choix fait est : ");
const choixTableInput = document.getElementById("tableInput");
const btn = document.getElementById("btnContinuer");
const formulaire = document.getElementById("formulaireValidationTable");
const modal = document.getElementById("myModal");
const textModal = document.getElementById("textModal");
var span = document.getElementsByClassName("close")[0];
span.onclick = function () {
    modal.style.display = "none";
};
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};
// const valeur = choixTableInput.value;
formulaire.addEventListener("submit", function (event) {
    if (choixTableInput.value == 0) {
        event.preventDefault();
        textModal.innerText =
            "Veuillez sélectionner le numéro de votre table s'il vous plait";

        modal.style.display = "block";

        // alert("Veuillez sélectionner le numéro de votre table s'il vous plait");
    }
    // else {
    //     const texte =
    //         "vous avez sélectionner la table numéro " + choixTableInput.value;
    //     textModal.innerText = texte;

    //     modal.style.display = "block";

    //     // "vous avez sélectionner la table numéro " + choixTableInput.value
    // }
});

// +choixTable.select("btnContinuer");
