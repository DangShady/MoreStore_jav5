$(document).ready(function() {
	
	$(document).on('click', '.btn-update-to-cart', function() {

		var id = document.querySelector("#idProductViewCart").value;
		var quantity = document.querySelector(".num-product").value;

		$.ajax({
			url: `/mstore/product/update-to-cart/${id}/${quantity}`,
			success: function(response) {
				$("#cart-amount").html(response[1]);
				setTimeout(function() {// wait for 5 secs(2)
					window.location.reload(); // then reload the page.(3)
				}, 700);
			}
		});
	});
	
})