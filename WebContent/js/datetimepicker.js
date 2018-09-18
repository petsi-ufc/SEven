$(document).ready(function()
	{
                $("#date-end-event").prop("disabled", true);
                $("#date-start-in").prop("disabled", true);
                $("#date-end-in").prop("disabled", true);
                
		$('.time').bootstrapMaterialDatePicker
		({
			date: false,
			shortTime: false,
			format: 'HH:mm'
		});
                               
                $('.date').bootstrapMaterialDatePicker
                ({
			format: 'DD/MM/YYYY',
			lang: 'pt',
			time: false,
			clearButton: false,
                        nowButton: true,
                        cancelText: 'Cancelar',
                        clearText: 'Limpar',
                        okText: 'Confirmar',
                        nowText: 'Hoje'
		});
                
                
                
                $('#datenasc').bootstrapMaterialDatePicker
                ({
			format: 'DD/MM/YYYY',
			lang: 'pt',
			time: false,
			clearButton: false,
                        nowButton: true,
                        cancelText: 'Cancelar',
                        clearText: 'Limpar',
                        okText: 'Confirmar',
                        nowText: 'Hoje',
                        maxDate: new Date()
		}).on('change', function(e, date)        
                    {
                        var data = new Date(date);
                        var day = data.getDate();
                        var month = data.getMonth();
                        if(month < 10){
                            month = "0" + (month + 1);
                        }
                        if(day < 10){
                            day = "0" + day;
                        }
                        $('#box_datenasc').val(day + "/" + month + "/" + data.getFullYear());                       
                    });                
                
                $('#date-end-event').bootstrapMaterialDatePicker
                ({ 
                       format: 'DD/MM/YYYY',
			lang: 'pt',
			time: false,
			clearButton: false,
                        nowButton: true,
                        cancelText: 'Cancelar',
                        clearText: 'Limpar',
                        okText: 'Confirmar',
                        nowText: 'Hoje',
                        minDate: new Date()  
                }).on('change', function(e, date)        
                    {
                        $('#date-end-in').bootstrapMaterialDatePicker('setMaxDate', date);
                        $('#date-start-event').bootstrapMaterialDatePicker('setMaxDate', date);
                        $("#date-start-in").val("");
                        $("#date-end-in").val("");
                    });
                
                $('#date-start-event').bootstrapMaterialDatePicker
                ({
                        format: 'DD/MM/YYYY',
			lang: 'pt',
			time: false,
			clearButton: false,
                        nowButton: true,
                        cancelText: 'Cancelar',
                        clearText: 'Limpar',
                        okText: 'Confirmar',
                        nowText: 'Hoje',
                        minDate: new Date()
                }).on('change', function(e, date)        
                    {
                        $('#date-end-event').bootstrapMaterialDatePicker('setMinDate', date);
                        $('#date-start-in').bootstrapMaterialDatePicker('setMaxDate', date);
                        $("#date-end-event").prop("disabled", false);
                        $("#date-start-in").prop("disabled", false);
                        $("#date-start-in").val("");
                        $("#date-end-in").val("");
                    });
                    
                $('#date-end-in').bootstrapMaterialDatePicker
                ({ 
                       format: 'DD/MM/YYYY',
			lang: 'pt',
			time: false,
			clearButton: false,
                        nowButton: true,
                        cancelText: 'Cancelar',
                        clearText: 'Limpar',
                        okText: 'Confirmar',
                        nowText: 'Hoje',
                        minDate: new Date()  
                });
                
                $('#date-start-in').bootstrapMaterialDatePicker
                ({
                        format: 'DD/MM/YYYY',
			lang: 'pt',
			time: false,
			clearButton: false,
                        nowButton: true,
                        cancelText: 'Cancelar',
                        clearText: 'Limpar',
                        okText: 'Confirmar',
                        nowText: 'Hoje',
                        minDate: new Date()
                }).on('change', function(e, date)        
                    {
                        $('#date-end-in').bootstrapMaterialDatePicker('setMinDate', date);
                        $("#date-end-in").prop("disabled", false);
                    });
                
		$.material.init();
	});