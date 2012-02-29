function actualizeTimeFields(field)
{
    var startHour = document.getElementById('activity:startTimeHour');
    var startMinute = document.getElementById('activity:startTimeMinute');

    var endHour = document.getElementById('activity:endTimeHour');
    var endMinute = document.getElementById('activity:endTimeMinute');

    var duration = document.getElementById('activity:duration');

    var aDate = new Date();
    aDate.setHours(parseInt(startHour.value));
    aDate.setMinutes(parseInt(startMinute.value));
    aDate.setSeconds(0);

    if ((field.id == "activity:startTimeHour") || (field.id == "activity:duration"))
    {
        aDate.setTime(aDate.getTime() + parseFloat(duration.value) * 60 * 60 * 1000);
        endMinute.value = aDate.getMinutes();
        return aDate.getHours();
    }

    if (field.id == "activity:startTimeMinute")
    {
        aDate.setTime(aDate.getTime() + parseFloat(duration.value) * 60 * 60 * 1000);
        endHour.value = aDate.getHours();
        return aDate.getMinutes();
    }

    if ((field.id == "activity:endTimeHour") || (field.id == "activity:endTimeMinute"))
    {
        var bDate = new Date();
        bDate.setTime(aDate.getTime());
        bDate.setHours(parseInt(endHour.value));
        bDate.setMinutes(parseInt(endMinute.value));
        bDate.setSeconds(0);

        return (bDate.getTime() - aDate.getTime()) / (60 * 60 * 1000);
    }
}