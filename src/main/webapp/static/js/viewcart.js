const button = document.querySelector("button.btn.btn-outline-info.viewcartbutton")
console.log(button)


button.addEventListener("click", get)
async function get() {
    let data = await fetchCart()
    console.log(data)
    let total = 0

    let cartitemsHTML = ``

    for (let item of data) {
        let subtotal = item.defaultPrice * item.quantity
        total += subtotal

        cartitemsHTML += `
        <tr>
                                <td>
                                    <div class="row">
                                        <div class="col-lg-2 Product-img">
                                            <img src='static/img/product_${item.id}.jpg' alt="..." class="img-responsive" />
                                        </div>
                                        <div class="col-lg-10">
                                            <h4 class="nomargin">${item.name}</h4>
                                        </div>
                                    </div>
                                </td>
                                <td>$${item.defaultPrice} </td>
                                <td data-th="Quantity">
                                    <input type="number" class="form-control text-center quantitychanger" value="${item.quantity}" min="0" max="168" data-id="${item.id}">
                                </td>
                                <td>USD ${subtotal}</td>
                                <td class="actions" data-th="" style="width:10%;">
                                    <button class="btn btn-warning btn-sm"><i class="fa fa-refresh"></i></button>
                                    <button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>
                                </td>
                            </tr>
        `

        document.getElementById("cartitemsplace").innerHTML = cartitemsHTML

    }

    document.querySelectorAll(".quantitychanger").forEach(item => item.addEventListener('change', async ()=> {
        await changeCart(item.dataset.id, item.value)
        await get()

    }))

    document.getElementById("totalprice").innerText="Total:  " + total

}

async function fetchCart() {
    try {
        const response = await fetch(`/`, {method: 'POST', body:''})
        console.log(response)

        return await response.json()
    } catch (e) {
        console.log('error', e)
    }
}

async function changeCart(id, quantity) {
    try {
        await fetch(`/?id=${id}&amount=${quantity}`)
    } catch (e) {
        console.log('error', e)
    }
}