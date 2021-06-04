$(document).ready(function() {
	
	$(document).on('click', '.btn-remove-to-cart', function() {
			
		var id = $(this).closest("tr").attr("id");
		
		$.ajax({
			url: "/mstore/product/remove-to-cart/" + id,
			success: function(response) {
				$("#cart-amount").html(response[1]);
				setTimeout(function() {// wait for 5 secs(2)
					window.location.reload(); // then reload the page.(3)
				}, 800);
			}
		});
	});
	
})