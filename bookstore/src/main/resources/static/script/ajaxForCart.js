$("#btn-add-to-cart").click(function() {
		
		
	 	var id = $("#idBook").val(); 
		var quantity = $("#quantity").val(); 
	$.ajax({
		
		type: "POST",
        url: "http://localhost:8080/add-to-cart",
		data: {
			id: id, 
			quantity:quantity
		},
		success: function(value) {
			
			if (value == "true") {
				alert("Thêm vào giỏ hàng thành công!"); 
			}else{
				alert("Thêm thất bại");  
			}
		},error: () => {
		console.log('Error');
	}

	})
})


$(".quantity-book").change(function() {
	var block = $(this).closest("div"); 
	var bookId = $(this).closest("div").find(".book").attr("data-idBook"); 
	var quantity = $(this).val(); 
	 
	
	
	$.ajax({
		url: "/update-cart",
		type: "POST",
		data: {
			id : bookId,
			quantity: quantity
		},
		success: function(value) {
			
			 if(value=="1"){ //vượt quá số lượng hiện có
				alert("Vượt quá số lượng hiện có!"); 
				window.location.replace("/cart/");
				 
			}else{
				
				var price = block.find(".book-price").attr("data-book-price");
				var priceOfItem = price*quantity; 
				var priceOfItemFormat = priceOfItem.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").replace(".00", "");
				var totalPriceFormat = parseInt(value).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").replace(".00", "");
				block.find(".item-price").replaceWith('<p class="item-price">Thành tiền <span style="font-weight:bold">' + priceOfItemFormat + ' VND </span> </p>'); 
				$("#total-price").replaceWith('<p style="font-size:25px" id= "total-price">Tổng tiền <span style="font-weight:bold; color:red">' + totalPriceFormat + ' VND </span> </p>');
			}
		}
	})
})




$(".btn-delete-item").click(function() {
		
		var block = $(this).closest("div"); 
	 	var bookId = $(this).closest("div").find(".book").attr("data-idBook"); 
		 
	$.ajax({
		
		type: "POST",
        url: "http://localhost:8080/remove-item",
		data: {
			id: bookId
			
		},
		success: function(value) {
			
			if (value == "false") {
				alert("Xóa thất bại"); 
				
			}else{
				
				
				block.remove();  
				var totalPriceFormat = parseInt(value).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,").replace(".00", "");
				$("#total-price").replaceWith('<p style="font-size:25px" id= "total-price">Tổng tiền <span style="font-weight:bold; color:red">' + totalPriceFormat + ' VND </span> </p>'); 
			}
		},error: () => {
		console.log('Error');
	}

	})
})

















