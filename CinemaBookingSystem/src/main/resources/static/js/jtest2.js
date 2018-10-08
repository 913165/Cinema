$(function() {
	createseating();
});
// Note:In js the outer loop runs first then the inner loop runs completely so
// it goes o.l. then i.l. i.l .i.l .i.l. i.l etc and repeat
var bookedSeats = [ 1, 2, 3, 4, 5, 6 ];
function createseating() {
	var count = 0;
	var seatingValue = [];
	for (var i = 0; i < 10; i++) {
		for (var j = 0; j < 10; j++) {
			var seatingStyle = "";
			if ($.inArray(count, bookedSeats) >= 0) {
				seatingStyle = "<div class='seat available selected makereadonly' ></div>";
			} else {
				seatingStyle = "<div class='seat available'></div>";
			}
			seatingValue.push(seatingStyle);
			count++;
			if (j === 9) {
				var seatingStyle = "<div class='clearfix'></div>";
				seatingValue.push(seatingStyle);
			}
		}
	}
	$('#messagePanel').html(seatingValue);
	var str = $('#strCurrentBookedSeats').val();
	$(function() {
		$('.seat')
				.on(
						'click',
						function() {
							if ($(this).hasClass("selected")) {
								console.log($(this).index());
								console.log("test");
								console.log($("#gold").html());
								$('#gold').html('Gold: ' + $(this).index());
								$(this).removeClass("selected");
								$('#box' + $(this).index()).remove();
							} else {
								$('#firstname').val('Gold: ' + $(this).index());
								$(this).addClass("selected");
								$("#listDiv")
										.append(
												'&nbsp;<input readonly id = "box'
														+ $(this).index()
														+ '" name="selectedSeats" size="1" type="text" value="'
														+ $(this).index()
														+ '">');
							}
						});
	});
};

