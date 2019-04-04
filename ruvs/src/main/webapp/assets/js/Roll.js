/**
 * Created by Randy on 2016/8/3.
 */
$(function () {
    var curr = new Date().getFullYear();

    var opt = {
        'default': {
            theme: 'default',
            mode: 'scroller',
            display: 'modal',
            animate: 'fade'
        },
        'dateY': {
            preset: 'date',
            dateFormat: 'yyyy',
            defaultValue: new Date(new Date()),
            invalid: { daysOfWeek: [0, 6], daysOfMonth: ['5/1', '12/24', '12/25'] },
            onBeforeShow: function (inst) {
                if(inst.settings.wheels[0].length>1)
                {
                    inst.settings.wheels[0].pop();
                    inst.settings.wheels[0].pop();
                }else{
                    null
                }
            }
        },
        'dateYM': {
            preset: 'date',
            dateFormat: 'yyyy-mm',
            defaultValue: new Date(new Date()),
            onBeforeShow: function (inst) {
                if(inst.settings.wheels[0].length>2)
                {
                    inst.settings.wheels[0].pop();
                }else{
                    null
                }
            }
        },
        'dateYMD': {
            preset: 'date',
            dateFormat: 'yyyy-mm-dd',
            defaultValue: new Date(new Date()),


        },
        'dateYMD1': {
            preset: 'date',
            dateFormat: 'yyyymmdd',
            defaultValue: new Date(new Date()),


        },
        'datetime': {
            preset: 'datetime',
            minDate: new Date(2012, 3, 10, 9, 22),
            maxDate: new Date(2014, 7, 30, 15, 44),
            stepMinute: 5
        },
        'time': {
            preset: 'time'
        },
        'select': {
            preset: 'select'
        },
        'select-opt': {
            preset: 'select',
            group: true,

        }

    }
    $('#demo1').scroller($.extend(opt['dateY'],opt['default']));
    $('#demo2').scroller($.extend(opt['dateYM'],opt['default']));
    $('#demo3').scroller($.extend(opt['dateYMD'],opt['default']));
    $('.demo4').scroller($.extend(opt['dateYMD1'],opt['default']));

    $('.demo-test-select').scroller($.extend(opt['select'],opt['default']));
    $('.demo-test-select-opt').scroller($.extend(opt['select-opt'],opt['default']));

});