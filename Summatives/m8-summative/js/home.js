$(document).ready(function(){
    moneyIn = 0;
clearAll();
loadItemList();
$('#moneyDisplay').text('$' + moneyIn.toFixed(2));

$('#dollar').click(function(event){
    $('#changeBack').text('');
     moneyIn += 1.00;
     $('#moneyDisplay').text('$' + moneyIn.toFixed(2));
});

$('#quarter').click(function(event){
    $('#changeBack').text('');
    moneyIn += 0.25;
    $('#moneyDisplay').text('$' + moneyIn.toFixed(2));
});

$('#dime').click(function(event){
    $('#changeBack').text('');
    moneyIn += 0.10;
    $('#moneyDisplay').text('$' + moneyIn.toFixed(2));
});

$('#nickel').click(function(event){
    $('#changeBack').text('');
    moneyIn += 0.05;
    $('#moneyDisplay').text('$' + moneyIn.toFixed(2));
});

$('#getChange').click(function(event){
    returnChange(moneyIn);
    moneyIn = 0.00;
    $('#messageDisplay').text('');
    $('#itemInput').val('');
});

$(document).on('click', '.panel-body', function(event){
    $('#messageDisplay').text('');
    $('#changeBack').text('');
    var id = $(this).attr('id');

    $('#itemInput').val(id.replace('item', ''));
});


$('#purchaseBtn').click(function(event){
    var viewId = $('#itemInput').val();
    var itemId = $('#item' + viewId + ' > .itemIdHolder').attr('id');

    $.ajax({
        type: 'POST',
        url: 'http://tsg-vending.herokuapp.com/money/' + moneyIn + '/item/' + itemId,
        success: function(response){
            var quarters = response.quarters;
            var dimes = response.dimes;
            var nickels = response.nickels;
            var pennies = response.pennies;
            var textToDisplay = '';

            if(quarters > 0){
                textToDisplay += quarters + ' Quarter(s)';
            }
            if(dimes > 0 && quarters > 0){
                textToDisplay += ', ' + dimes + ' Dime(s)';
            } else if(dimes > 0){
                textToDisplay += dimes + ' Dime(s)';
            }
            if(nickels > 0 && (dimes > 0 || quarters > 0)){
                textToDisplay += ', ' + nickels + ' Nickel(s)';
            } else if(nickels > 0){
                textToDisplay += nickels + ' Nickel(s)';
            }
            if(pennies > 0 && (nickels > 0 || dimes > 0 || quarters > 0)){
                textToDisplay += ', ' + pennies + ' Pennies';
            } else if(pennies > 0){
                textToDisplay += pennies + ' Pennies';
            }
            $('#changeBack').text(textToDisplay);
            $('#messageDisplay').text("Thank You!!");
            moneyIn = 0;
            $('#moneyDisplay').text(moneyIn.toFixed(2));
            loadItemList();
        },
        error: function(jqXHR, exception){
            if(jqXHR.status === 422){
                var errorMessage = jQuery.parseJSON(jqXHR.responseText).message;
                $('#messageDisplay').text(errorMessage);
             }
        } 
    });
    
    
});
})


function clearItems(){
    $('.itemPanel').remove();
}

function clearAll(){
    $('.itemInfo').text('');
    $('#moneyDisplay').text('');
    $('#messageDisplay').text('');
    $('#changeBack').text('');
    $('#itemInput').val('');
}


function returnChange(moneyIn){
    var quarters = 0;
    var dimes = 0;
    var nickels = 0;
    var pennies = 0;
    var moneyOut = 0;
    moneyOut = moneyIn.toFixed(2);
    moneyOut = (moneyOut * 100.00).toFixed(2);
    var textToDisplay = '';
    
    while(moneyOut > 0){
        if(moneyOut >= 25){
            moneyOut -= 25;
            quarters += 1;
        } else if(moneyOut >= 10){
            moneyOut -= 10;
            dimes += 1;
        } else if(moneyOut >= 5){
            moneyOut -= 5;
            nickels += 1;
        } else if(moneyOut >= 1){
            moneyOut -= 1;
            pennies += 1;
        }
    }


    if(quarters > 0){
        textToDisplay += quarters + ' Quarter(s)';
    }
    if(dimes > 0 && quarters > 0){
        textToDisplay += ', ' + dimes + ' Dime(s)';
    } else if(dimes > 0){
        textToDisplay += dimes + ' Dime(s)';
    }
    if(nickels > 0 && (dimes > 0 || quarters > 0)){
        textToDisplay += ', ' + nickels + ' Nickel(s)';
    } else if(nickels > 0){
        textToDisplay += nickels + ' Nickel(s)';
    }
    if(pennies > 0 && (nickels > 0 || dimes > 0 || quarters > 0)){
        textToDisplay += ', ' + pennies + ' Pennies';
    } else if(pennies > 0){
        textToDisplay += pennies + ' Pennies';
    }
    moneyIn = 0;
    $('#changeBack').text(textToDisplay);
    $('#moneyDisplay').text(moneyIn.toFixed(2));

}


function loadItemList(){
    clearItems();
    
    $.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function(itemArray){
            $.each(itemArray, function(index, item){
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;
                
                var panel = '<div class="panel panel-primary itemPanel">';
                panel += '<div class="panel-body" id="item';
                panel += (index +1);
                panel += '">';
                panel += '<div class="row">';
                panel += '<div class="col-xs-12"><div class="pull-left" style="margin-top: 0;">';
                panel += (index + 1);
                panel += '</div></div></div><div class="itemIdHolder"></div>';
                panel += '<div class="row text-center itemInfo" id="item';
                panel += (index + 1);
                panel += 'name"></div><div class="row text-center itemInfo" id="item';
                panel += (index + 1);
                panel += 'price"></div><div class="row text-center itemInfo" id="item';
                panel += (index + 1);
                panel += 'stock"></div></div></div>';

                if(index == 0 || index == 3 || index == 6){
                   
                    $('#column1').append(panel);
                    
                    $('#item' + (index+1)+ ' >.itemIdHolder').attr('id', id);
                    $('#item' + (index+1) + 'name').text(name);
                    $('#item' + (index+1) + 'price').text('$' + price.toFixed(2));
                    $('#item' + (index+1) + 'stock').text('Quantity Left: ' + quantity);
                } else if(index == 1 || index == 4 || index == 7){
                    $('#column2').append(panel);
                    
                    $('#item' + (index+1)+ ' >.itemIdHolder').attr('id', id);
                    $('#item' + (index+1) + 'name').text(name);
                    $('#item' + (index+1) + 'price').text('$' + price.toFixed(2));
                    $('#item' + (index+1) + 'stock').text('Quantity Left: ' + quantity);
                } else if(index == 2 || index == 5 || index == 8){
                    $('#column3').append(panel);
                    
                    $('#item' + (index+1)+ ' >.itemIdHolder').attr('id', id);
                    $('#item' + (index+1) + 'name').text(name);
                    $('#item' + (index+1) + 'price').text('$' + price.toFixed(2));
                    $('#item' + (index+1) + 'stock').text('Quantity Left: ' + quantity);
                } 
            });
        },
        error: function(){
            $('#errorMessages').append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'))
        } 
    });
}