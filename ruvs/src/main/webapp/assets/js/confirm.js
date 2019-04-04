$(document).ready(function(){
	
	$('.list .set_datele').click(function(){
		
		var elem = $(this).closest('.list');
		
		$.confirm({
			'title'		: 'Delete Confirmation',
			'message'	: '确定要删除吗?',
			'buttons'	: {
				'取消'	: {
					'class'	: 'blue',
					'action': function(){}
				},
				'确定'	: {
					'class'	: 'gray',
						// Nothing to do in this case. You can as well omit the action property.
					'action': function(){
						elem.slideUp();
					}
				}
			}
		});
		
	});

});