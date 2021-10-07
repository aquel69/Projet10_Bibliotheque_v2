var arretEffet = false;
if(arretEffet === false){

    var textWrapper = document.querySelector('.ml3');
    textWrapper.innerHTML = textWrapper.textContent.replace(/\S/g, "<span class='letter'>$&</span>");

    anime.timeline({loop: true})
        .add({
            targets: '.ml3 .letter',
            opacity: [0,1],
            easing: "easeInOutQuad",
            duration: 2250,
            delay: (el, i) => 150 * (i+1)
        }).add({
        targets: '.ml3',
        opacity: 0,
        duration: 1000,
        easing: "easeOutExpo",
        delay: 100000
    })
    arretEffet = true;
};

let input = document.querySelector(".input");


/*for(i = 0; i < ele.length; i++) {
    if(ele[i].checked)
        console.log(ele[i].value);
}*/
let button = document.querySelector("#boutonSupprimerReservation");
console.log("input" + input);

if (document.querySelector(".input").value === "") {
    button.disabled = true; //button remains disabled
} else {
    button.disabled = false; //button is enabled
}
console.log(button.disabled);

button.disabled = true;

/*button.disabled = true;
input.addEventListener("change", stateHandle);

function stateHandle() {
    if(document.querySelector(".input").value === "") {
        button.disabled = true;
    } else {
        button.disabled = false;
    }
}*/



