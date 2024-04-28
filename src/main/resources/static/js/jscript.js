$(document).ready(function() {
    // Add a click event handler to the button
    $('#colorButton').click(function() {
        // Change the button color to a different Bootstrap color class
        $(this).removeClass('btn-primary').addClass('btn-success');

        // You can also toggle between colors using toggleClass
        // $(this).toggleClass('btn-primary btn-success');
    });
});


//first Request to server to create order


const paymentStart=(amount)=>{
	
	console.log("Log started....");
	
	//code..
	//we will use Ajax to create Order
	
	
};
