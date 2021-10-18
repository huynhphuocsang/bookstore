
 const frmOrderDetail = $(".container-order-detail");
 const btnEditOrder = $(".btn__edit--order");

/* =================EVENT BUTTON EDIT ORDER============== */
btnEditOrder.click(() => {
	$(".main__overlay").css("display", "block");
	frmOrderDetail.css("display", "block");
	
	/* EVENT CLOSE FORM ADD BOOK */
	$(".btn.btn-close-from").click((e) => {
		$(".main__overlay").css("display", "none");
		frmOrderDetail.css("display", "none");
	})
});

/* =================EVENT CHANGE STATUS ORDER============== */
const btnsStatus = $(".track .icon");

btnsStatus.each((i,btn)=>{
	btn.onclick=()=>{
		console.log("click")
		btn.parentElement.classList.toggle('active');
		let preSibling=btn.parentElement;
		let nextSibling=btn.parentElement;
		while (preSibling = preSibling.previousElementSibling) {
	        preSibling.classList.add('active');
	    }
	    
	    while (nextSibling = nextSibling.nextElementSibling) {
	        nextSibling.classList.remove('active');
	    }
	}
})