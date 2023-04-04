
function init() {
    // Get form element
    const bodyElement = document.getElementById("body");

    // Fetch order
    fetch("http://localhost:8080/session").then(res => res.json().then(order => {
        let html = "";
        Object.keys(order).forEach((day, index) => {
            const dayName = day.at(0).toUpperCase() + day.slice(1); // Get formatted name
            html += "<tr>" +
                "       <th scope=\"row\">" + (index + 1) + "</th>" +
                "       <td>" + dayName + "</td>" +
                "       <td>" + order[day] + "</td>" +
                "    </tr>"
        })
        bodyElement.innerHTML += html;
    })).catch(err => {
        console.log(err.message);
    });
}