
$(document).ready(function () {
    loadItems();

    $('#buy-button').click(function (event) {
        //event.preventDefault();
        
        $.ajax({
            type: 'POST',
            url: 'itemId/' + $('#itemNumber').val() + '/userMoney/' + $('#userMoney').val(),
            data: JSON.stringify({
                itemId: $('#itemNumber').val(),
                userMoney: $('userMoney').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'data-Type': 'json'
        }).success(function (data, status) { //data = PurchaseResult
            if (data.sufficientFunds && data.sufficientQuant) {
                //display item and change
                displayPositiveVendingResults(data, status);
                loadItems();
            } else if (!data.sufficientQuant) {
                //display Item is no longer available.
                displayInsufficientQuantity();
            } else if (!data.sufficientFunds) {
                //display Insufficient funds.
                displayInsufficientFunds();
            }

            $('#itemNumber').val('');
            $('#userMoney').val('');

        });

    });

});

function loadItems() {
    var display = $('#displayItemsDiv');
    display.empty();

    $.ajax({
        type: 'GET',
        url: 'items',
        headers: {
            'Accept': 'application/json'
        }
    }).success(function (data, status) {
        fillDisplayDiv(data, status);
    });
}

function fillDisplayDiv(itemList, status) {
    var display = $('#displayItemsDiv');
    display.empty();

    $.each(itemList, function (index, item) {

        display.append('Item Number: ' + item.itemId
                + '<br> Item Name: ' + item.itemName
                + '<br> Price: ' + item.itemPrice
                + '<br> Quantity: ' + item.itemQuantity
                + '<br> <hr />');
    });

}

function displayPositiveVendingResults(purchaseResults, status) {
    var display = $('#purchaseResults');
    display.empty();

    display.append('Here is your item! <br> Item Name: ' + purchaseResults.item.itemName
            + '<br> Change: ' + purchaseResults.change);

}

function displayInsufficientFunds() {
    var display = $('#badInputSection');
    display.empty();

    display.append('Insufficient funds.');

}

function displayInsufficientQuantity() {
    var display = $('#badInputSection');
    display.empty();

    display.append('Item is no longer available.');

}
