/**
 * marks all rows and selects its first checkbox inside the given element
 * the given element is usaly a table or a div containing the table or tables
 *
 * @param    container    DOM element
 */
function markAllRows( container_id ) {
    var rows = document.getElementById(container_id).getElementsByTagName('tr');
    var unique_id;
    var checkbox;

    for ( var i = 0; i < rows.length; i++ ) {

        checkbox = rows[i].getElementsByTagName( 'input' )[0];

        if ( checkbox && checkbox.type == 'checkbox' ) {
            unique_id = checkbox.name + checkbox.value;
            checkbox.checked = true;
            rows[i].className = rows[i].className.replace(' marked', '');
            marked_row[unique_id] = true;
        }
    }

    return true;
}

/**
 * marks all rows and selects its first checkbox inside the given element
 * the given element is usaly a table or a div containing the table or tables
 *
 * @param    container    DOM element
 */
function unMarkAllRows( container_id ) {
    var rows = document.getElementById(container_id).getElementsByTagName('tr');
    var unique_id;
    var checkbox;

    for ( var i = 0; i < rows.length; i++ ) {

        checkbox = rows[i].getElementsByTagName( 'input' )[0];

        if ( checkbox && checkbox.type == 'checkbox' ) {
            unique_id = checkbox.name + checkbox.value;
            checkbox.checked = false;
            rows[i].className = rows[i].className.replace(' marked', '');
            marked_row[unique_id] = false;
        }
    }

    return true;
}

/**
 * Displays an confirmation box before to submit a "DROP/DELETE/ALTER" query.
 * This function is called while clicking links
 *
 * @param   object   the link
 * @param   object   the sql query to submit
 *
 * @return  boolean  whether to run the query or not
 */
function confirmLink(theLink, theSqlQuery)
{
    // Confirmation is not required in the configuration file
    // or browser is Opera (crappy js implementation)
    if (confirmMsg == '' || typeof(window.opera) != 'undefined') {
        return true;
    }

    var is_confirmed = confirm(confirmMsg + ' :\n' + theSqlQuery);
    if (is_confirmed) {
        if ( typeof(theLink.href) != 'undefined' ) {
            theLink.href += '&is_js_confirmed=1';
        } else if ( typeof(theLink.form) != 'undefined' ) {
            theLink.form.action += '?is_js_confirmed=1';
        }
    }

    return is_confirmed;
} // end of the 'confirmLink()' function

/**
 * Checks/unchecks all checkbox in given conainer (f.e. a form, fieldset or div)
 *
 * @param   string   container_id  the container id
 * @param   boolean  state         new value for checkbox (true or false)
 * @return  boolean  always true
 */
function setCheckboxes( container_id, state ) {
    var checkboxes = document.getElementById(container_id).getElementsByTagName('input');

    for ( var i = 0; i < checkboxes.length; i++ ) {
        if ( checkboxes[i].type == 'checkbox' ) {
            checkboxes[i].checked = state;
        }
    }

    return true;
} // end of the 'setCheckboxes()' function
