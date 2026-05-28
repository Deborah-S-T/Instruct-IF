/**
 * En-tête commun Instruct-IF (schéma navigation)
 */
function renderHeader(options) {
    options = options || {};
    var links = options.links || [];
    var brandHref = options.brandHref || 'index.html';

    var header = document.createElement('header');
    header.className = 'app-header';

    var brand = document.createElement('a');
    brand.className = 'brand';
    brand.href = brandHref;
    brand.innerHTML = 'Instruct<span>-IF</span>';
    header.appendChild(brand);

    if (links.length > 0) {
        var nav = document.createElement('nav');
        nav.className = 'app-nav';
        links.forEach(function (link) {
            if (link.href) {
                var a = document.createElement('a');
                a.href = link.href;
                a.textContent = link.label;
                nav.appendChild(a);
            } else if (link.onclick) {
                var btn = document.createElement('button');
                btn.type = 'button';
                btn.className = 'nav-btn';
                btn.textContent = link.label;
                btn.addEventListener('click', link.onclick);
                nav.appendChild(btn);
            }
        });
        header.appendChild(nav);
    }

    document.body.insertBefore(header, document.body.firstChild);

    var main = document.querySelector('[data-main]');
    if (main && !main.classList.contains('app-main')) {
        main.classList.add('app-main');
    }
}

async function fetchJson(url) {
    try {
        var response = await fetch(url);
        return await response.json();
    } catch (e) {
        console.log(e);
        return null;
    }
}
