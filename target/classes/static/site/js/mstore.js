$(document).ready(function() {
	$(document).on('click', '.btn-add-to-cart', function() {

		var id = document.querySelector("#idProduct").value;

		$.ajax({
			url: "/mstore/product/add-to-cart/" + id,
			success: function(response) {
				//$("#cart-amount").html(response[1]);			

				setTimeout(function() {// wait for 5 secs(2)
					window.location.reload(); // then reload the page.(3)
				}, 800);
			}
		});
	});
	
})

