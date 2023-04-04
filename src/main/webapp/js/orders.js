
function init() {
    // Get orders element
    const ordersElement = document.getElementById("orders");

    // Get password
    const url = window.location.href;
    const password = url.split("?").at(1).split("=").at(1);

    // Fetch order
    fetch(`http://localhost:8080/api/orders?password=${password}`).then(res => res.json().then(orders => {
        Object.keys(orders).forEach(day => {
            const dayName = day.at(0).toUpperCase() + day.slice(1); // Get formatted name

            let html = "<div class=\"wrapper\">"
            html += "<h2>" + dayName + "</h2>"; // Set header
            html += "<table class=\"table table-bordered\">\n" +
                "       <thead class=\"table-dark\">\n" +
                "           <tr>\n" +
                "                <th scope=\"col\">#</th>\n" +
                "                <th scope=\"col\">Meal</th>\n" +
                "                <th class=\"right\" scope=\"col\">Quantity</th>\n" +
                "            </tr>\n" +
                "       </thead>\n" +
                "       <tbody>"

            let meals = orders[day];
            Object.keys(meals).forEach((meal, index) => {
                html += "<tr>" +
                    "       <th scope=\"row\">" + (index + 1) + "</th>" +
                    "       <td>" + meal + "</td>" +
                    "       <td class=\"right\">" + meals[meal] + "</td>" +
                    "    </tr>"
            });

            html += "</tbody></table></div>";

            ordersElement.innerHTML += html;
        });
    })).catch(err => {
        console.log(err.message);
    });
}

function reset() {
    fetch("http://localhost:8080/api/orders", {method: "DELETE"}).then(() => {
        window.location.reload();
    }).catch(err => {
        console.log(err.message);
    });
}