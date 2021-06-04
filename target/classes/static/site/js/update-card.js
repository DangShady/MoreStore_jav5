$(document).ready(function() {
	
	$(document).on('input', 'tr[data-id] input', function() {

		var id = $(this).closest("tr").attr("id");
		var quantity = $(this).val();

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