$(document).ready(function() {
	$(".btn-add-to-cart").click(function() {

		var id = document.querySelector("#idProduct").value;

		$.ajax({
			url: "/mstore/product/add-to-cart/" + id,
			success: function(response) {
				$("#cart-amount").html(response[1]);
				$('.js-addcart-detail').each(function() {
					var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
					$(this).on('click', function() {
						swal(nameProduct, "Đã được thêm vào giỏ hàng !", "success");
					});
				});

				setTimeout(function() {// wait for 5 secs(2)
					window.location.reload(); // then reload the page.(3)
				}, 800);
			}
		})
	})
})