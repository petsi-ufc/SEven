var app = angular.module('app', ['ui.calendar', 'ui.bootstrap']);

app.controller('MyController', function($scope, $compile,uiCalendarConfig) {
    
    var hours = []; 
    var title = [];
    var day = [];
    var local = [];
    var minister =  [];
    var tds = document.getElementsByTagName("td");
    
    function formatHoursToCalendar (hours){
        var hi0 = hours.substring(0,2);
        var hf0 = hours.substring(3,5);
        
        var hi1 = hours.substring(9,11);
        var hf1 = hours.substring(12,14);
        
        return {hi: hi0 +':'+ hf0, hf: hi1 +':'+ hf1}; 
    }
    
    for (var i = 0; i < tds.length; i++) {
    // If it currently has the ColumnHeader class...
        if (tds[i].className == "hours") {
            hours.push(formatHoursToCalendar(tds[i].innerHTML));
        }else if(tds[i].className == "name"){
            title.push(tds[i].innerHTML);
        }else if(tds[i].className == "hidden"){
            day.push(tds[i].innerHTML);
        }else if(tds[i].className == "local"){
            local.push(tds[i].innerHTML);
        }else if(tds[i].className == "people"){
            minister.push(tds[i].innerHTML);
        }
    };  
    /* config object */
    $scope.uiConfig = {
      calendar:{
        eventRender: function( event, element, view ) {
        element.attr({ 
            "tooltip-placement":"top", 
            "uib-tooltip": event.title, 
            "tooltip-append-to-body": true 
        });
        $compile(element)($scope);
        },
        height: 450,
        editable: false,
        defaultView:'month',
        header:{
          left: 'title',
          center: '',
          right: 'today month agendaWeek prev,next'
        },
        eventClick: $scope.alertOnEventClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize,
        }
    };
    $scope.eventSource = {
            url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
            className: 'gcal-event',           // an option!
            currentTimezone: 'America/Chicago' // an option!
    };
    var events = [];
    for(var i = 0; i < title.length; i++){
        events.push({
            title: title[i] + "\n * " + local[i] + "\n * " + minister[i],
            start: (day[i] + " " + hours[i].hi), end: (day[i] + " " + hours[i].hf)
        });
    }
    $scope.events = events;
    /* event sources array*/
    $scope.eventSources = [$scope.events];
});

