function alert(text){
	$.messager.show({
		title:'系统提示',
		msg:text,
		showType:'null',
		showSpeed:300,
		timeout:1000,
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});

}