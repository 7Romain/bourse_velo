// const form = document.getElementById("filtrePersonne");
const champ = document.getElementById("searchInput");
// const btnEffacer = document.getElementById("btnEffacer");

/**
 *Fonction qui permet d'ajouter un espace tout les deux chiffres pour formatter les numéro de téléphones
 *
 */
let espaceTel = function () {
    const regex = /^[0-9][0-9,\s]+$/g;
    if (regex.test(champ.value)) {
        console.log(champ.value);
        console.log("chiffres");
        console.log(champ.value.length);

        switch (champ.value.length) {
            case 2:
                champ.value += " ";
                break;
            case 5:
                champ.value += " ";
                break;
            case 8:
                champ.value += " ";
                break;
            case 11:
                champ.value += " ";
                break;
        }
    } else {
        console.log("lettres");
    }
};

/**
 * Fonction qui filtre le tableau en cachant les elements qui ne corresponde pas à la recherche
 */
let filtrer = function () {
    espaceTel();
    let filtre, tableau, ligne, cellule, i, texte, texte2;
    filtre = champ.value.toUpperCase();
    tableau = document.getElementById("tableau");
    ligne = tableau.getElementsByTagName("tr");
    for (i = 0; i < ligne.length; i++) {
        cellule = ligne[i].getElementsByTagName("td")[1];
        cellule2 = ligne[i].getElementsByTagName("td")[2];

        if (cellule) {
            texte = cellule.innerText;
            texte2 = cellule2.innerText;
            if (texte.indexOf(filtre) > -1 || texte2.indexOf(filtre) > -1) {
                ligne[i].style.display = "";
            } else {
                ligne[i].style.display = "none";
            }
        }
    }
};
champ.addEventListener("keyup", () => filtrer());

/**
 *Si le champs contient un numéro de telephone alors 
 quand on appuie sur retour arrière on enlève le dernier chiffres.
 Sinon avec ma fonction qui ajoute des espaces il y a conflit
 * @param {} e
 */
let backspace = function (e) {
    const regex = /^[0-9][0-9,\s]+$/g;
    if (regex.test(champ.value)) {
        const regEspace = /\s$/;
        if (regEspace.test(champ.value)) {
            if (e.keyCode == 8) {
                champ.value = champ.value.substring(0, champ.value.length - 1);
            }
        }
    }
};
champ.addEventListener("keydown", (e) => backspace(e));
