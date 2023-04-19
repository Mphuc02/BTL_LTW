function showMenu() {
    var menu = document.getElementById("menu");
    if (menu.style.display === "block") {
      menu.style.display = "none";
    } else {
      menu.style.display = "block";
    }

    document.addEventListener("click", (event) => {
      if (!event.target.closest(".avatar")) {
        menu.style.display = "none";
      }
    }
    );
  }
  