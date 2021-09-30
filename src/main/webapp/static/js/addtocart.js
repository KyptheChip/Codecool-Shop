document.querySelectorAll(".cartbutton").forEach(button => button.addEventListener("click", async ()=> {
    let id = button.dataset.id
    await addToCart(id)
    window.location.reload()
}))


async function addToCart(id) {
    try {
        console.log(id)
        await fetch(`/?id=${id}`)
    } catch (e) {
        console.log('error', e)
    }
}