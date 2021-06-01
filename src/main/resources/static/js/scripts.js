/**
 * 
 */
$(document).ready(function(){
	$('.delete-shoe').on('click',function (){
		
		/*<![CDATA[*/
	    
        var path = /*[[@{/}]]*/'remove';

	    /*]]>*/

		var id=$(this).attr('id');
		
		bootbox.confirm({
			message :"Are you sure to delete all selected this book ?",
			buttons:{
				cancel:{
					label: '<i class="fa fa-times"></i> Cancel'
				},
				confirm:{
					label: '<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed){
				if(confirmed){
					$.post(path,{'id':id},function(res){
						location.reload();
					});
				}
			}
		});
	})
	
	
	var shoeIdList=[]
	
/*	$('.checkboxShoe').click(function(){
		var id=$(this).attr('id');
		if(thid.checked){
			shoeIdList.push(id);
		}
		else
		{
			shoeIdList.splice(shoeIdList.indexOf(id),1);
		}
		
	})*/
	
	$('#deleteSelected').click(function(){
		
		var idList= $('.checkboxShoe');
		for(var i=0;i<idList.length;i++)
		{
			shoeIdList.push(idList[i]['id'].substring(8))
		}
		
		/*<![CDATA[*/
	    
        var path = /*[[@{/}]]*/'removeList';

    	/*]]>*/
		
		bootbox.confirm({
			message :"Are yio sure to delete all selected this book ?",
			buttons:{
				cancel:{
					label: '<i class="fa fa-times"></i> Cancel'
				},
				confirm:{
					label: '<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed){
				if(confirmed){
					$.ajax({
						type:'POST',
						url: path,
						data: JSON.stringify(shoeIdList),
						contenType: "application/json",
						success: function(res){console.log(res);location.reload()},
						error: function(res){conole.log(res);location.reload();}
						
					});
					
				}
			}
		});


	});
	
	
	$("#selectAllShoes").click(function(){
		console.log("test");
		if($(this).prop("checked")==true){
			$(".checkboxShoe").prop("checked",true);
		}
		else if($(this).prop("checked")==false){
			$(".checkboxShoe").prop("checked",false);
		}
	})
});