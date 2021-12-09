
/* =================EVENT BUTTON ADD USER============== */
const btnAddUser = $(".btn__add--user");/* btn Add new book */
const frmAddUser = $(".from__add--user");/* Form add new book */

btnAddUser.click(() => {
	frmAddUser.removeClass("bounceOutUp")
	$(".main__overlay").css("display", "block");//làm mờ background
	clearnField();
	frmAddUser.css("display", "block");		// hiển thị form
	
	frmAddUser.find(".card-header:first").children("span").text("NEW USER") //đổi tiêu đề form
	frmAddUser.find(".card-header:first").css({"background-color": "#28A745","color": "white"})// đổi màu form
	
	/* EVENT CLOSE FORM ADD BOOK */
	$(".far.btn-close-from").click((e) => {
		$(".form-message.invalid").removeClass("invalid")
		frmAddUser.addClass("bounceOutUp")
		setTimeout(function(){
			frmAddUser.css("display", "none");
			$(".main__overlay").css("display", "none");
			clearnField();
		},500)
		
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
		clearnField();
		
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
	$('.form-row #userId').val(0);
	$('#user-name').val("");
	$('#user-age').val("");
	$('#user-phone').val("");
	$('#user-email').val("");
	$('#user-pass').val("");
	$('#user-confirm').val("");
}

function addAndEditNewUser(title="NEW USER", color="#28A745")
{
	$(".main__overlay").css("display", "block");//làm mờ background
	//clearnField();
	frmAddUser.css("display", "block");		// hiển thị form
	
	frmAddUser.find(".card-header:first").children("span").text(title) //đổi tiêu đề form
	frmAddUser.find(".card-header:first").css({"background-color": color,"color": "white"})// đổi màu form
	
	/* EVENT CLOSE FORM ADD BOOK */
	$(".far.btn-close-from").click((e) => {
		$(".main__overlay").css("display", "none");
		frmAddUser.css("display", "none");
		$(".form-message.invalid").removeClass("invalid")
		clearnField();
		
	})
}


