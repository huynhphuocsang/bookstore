
/* =================EVENT BUTTON ADD BOOK============== */
const btnAddUser = $(".btn__add--user");/* btn Add new book */
const frmAddUser = $(".from__add--user");/* Form add new book */

btnAddUser.click(() => {
	$(".main__overlay").css("display", "block");//làm mờ background
	frmAddUser.css("display", "block");		// hiển thị form
	
	frmAddUser.find(".card-header:first").children("span").text("NEW USER") //đổi tiêu đề form
	frmAddUser.find(".card-header:first").css({"background-color": "#28A745","color": "white"})// đổi màu form
	
	/* EVENT CLOSE FORM ADD BOOK */
	$(".far.btn-close-from").click((e) => {
		$(".main__overlay").css("display", "none");
		frmAddUser.css("display", "none");
		$(".form-message.invalid").removeClass("invalid")
		//clearnField();
		
	})
});

/* =================EVENT BUTTON EDIT user============== */
const btnEditUser = $(".btn__edit--user");/* btn edit  user */

btnEditUser.click(() => {
	$(".main__overlay").css("display", "block");
	frmAddUser.css("display", "block");
	//frmAddBook.attr("action","/admin/book/save")
	frmAddUser.find(".card-header:first").children("span").text("EDIT USER") 
	frmAddUser.find(".card-header:first").css({"background-color": "#FFC107","color": "black"})
	
	/* EVENT CLOSE FORM ADD BOOK */
	$(".far.btn-close-from").click((e) => {
		$(".main__overlay").css("display", "none");
		frmAddUser.css("display", "none");
		$(".form-message.invalid").removeClass("invalid")
		//clearnField();
		
	})
});



/* =================EVENT BUTTON DELETE USER============== */
const btnDeleteUser = $(".btn__delete--user");/* btn Add new book */
const diaDeleteUser = $(".from__confirm");/* Form add new book */
const inputDelete = $(".input-delete");

btnDeleteUser.click((e) => {
	$(".main__overlay").css("display", "block");
	let targetClass=$(e.target)
	if(targetClass.attr('class')=="far fa-trash-alt"){
		targetClass=targetClass.parent();
	}
	diaDeleteUser.find("#user-id").text(targetClass.attr("id"))
	inputDelete.val(targetClass.attr("id"))
	console.log(targetClass.attr("id"));
	diaDeleteUser.css("display", "block");
	
	/* EVENT CLOSE FORM */
	$(".btn-cancel").click((e) => {
		$(".main__overlay").css("display", "none");
		diaDeleteUser.css("display", "none");
	})
});

function clearnField(){
	$('.form-row #idBook').val("");
	$('#book-name').val("");
	$('#author').val("");
	$('#company').val("");
	$('#datepickerfrom').val("");
	$('#price').val("");
	$('#img-output').attr("src","/image/choosing-img-icon.png");
	$('#bookcategory').val("");
	$('#total-quantity').val("");
	$('#describe-book').val("");
	$('.option-input').each((i,input)=>{
		input.onchange();
	})
}



