/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
   loadAddresses(); 
});

function loadAddresses(){
    
    var cTableBody = $('#addressTableRows');
    
    $.each(testAddressData, function(index, address){
        cTableBody.append($('<tr>')
                .append($('<td>') //contact name
                        .append($('<a>') //link for contact name
                                .attr({
                                    'data-contact-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(address.firstName + ' ' + address.lastName)
                                )
                        )
                .append($('<td>').text(address.streetAddress))
                .append($('<td>')
                        .append($('<a>') //link for contact name
                                .attr({
                                    'data-contact-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                )
                        )
                .append($('<td>').text('Delete'))
                );
        
    });
    
}

//address details modal, this code runs in response to the show.bs.modal event
$('#detailsModal').on('show.bs.modal', function(event){
    
    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    
    var modal = $(this);
    modal.find('#address-id').text('42');
    modal.find('#address-firstName').text('Zip');
    modal.find('#address-lastName').text('Zippety');
    modal.find('#address-street-address').text('Zipper ln');
    modal.find('#address-city').text('Zippleton');
    modal.find('#address-state').text('ZP');
    modal.find('#address-zip').text('42224');
    
});

//edit details modal
$('#editModal').on('show.bs.modal', function(event){
    
    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    
    var modal = $(this);
    modal.find('#address-id').text('42');
    modal.find('#edit-firstName').text('Zip');
    modal.find('#edit-lastName').text('Zippety');
    modal.find('#edit-street-address').text('Zipper ln');
    modal.find('#edit-city').text('Zippleton');
    modal.find('#edit-state').text('ZP');
    modal.find('#edit-zip').text('42224');
    modal.find('#edit-addressId').val(addressId);
    
});

var testAddressData = [
    {
        addressId: 1,
        firstName: "Eliza",
        lastName: "Beth",
        streetAddress: "12 Blue Ln",
        city: "Berry",
        state: "NY",
        zip: "11111"
    },
     {
        addressId: 2,
        firstName: "Eliza",
        lastName: "Belle",
        streetAddress: "12 Rasp Ln",
        city: "Berry",
        state: "MA",
        zip: "2222"
    },
     {
        addressId: 3,
        firstName: "Rasp",
        lastName: "Berry",
        streetAddress: "12 Blue Ln",
        city: "Razzle",
        state: "IN",
        zip: "33333"
    },
     {
        addressId: 4,
        firstName: "Summer",
        lastName: "Time",
        streetAddress: "12 Apple Ln",
        city: "Orchard",
        state: "CA",
        zip: "44444"
    },
     {
        addressId: 5,
        firstName: "Poppy",
        lastName: "Seed",
        streetAddress: "12 Cinn Ln",
        city: "Amon",
        state: "AL",
        zip: "55555"
    }
      
];