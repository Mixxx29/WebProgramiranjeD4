
function init() {
    // Get form element
    const form = document.getElementById("form");

    // Fetch menu
    fetch("http://localhost:8080/menu").then(res => res.json().then(menu => {
        menu.forEach(day => {
            // Create day label
            let dayName = day.name.charAt(0) + day.name.slice(1).toLowerCase();
            let html = "<label for=\"" + dayName.toLowerCase() + "\" class=\"form-label\">" +
                dayName +
                "</label>";

            // Create selection menu
            html += "<select id=\"" + dayName.toLowerCase() + "\" name=\"" + dayName.toLowerCase() + "\" class=\"form-select\">";
            html += "<option value=\"Nothing\" selected>Nothing</option>";
            day.meals.forEach(meal => {
                html += "<option value=\"" + meal + "\">" + meal + "</option>"
            })
            html += "</select>";

            form.innerHTML += html;
        })
    })).catch(err => {
        console.log(err.message);
    })
}