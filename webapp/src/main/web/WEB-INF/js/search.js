$("#substr").autocomplete({
    source: function (request, response) {
        $.ajax({
            url: '<c:url value="/search/substr"/>',
            dataType: "json",
            data: {
                search: request.term
            },
            success: function (data) {
                response($.map(data, function (searchRes) {
                    if (searchRes.surname != null) {
                        return {
                            label: searchRes.name + ' ' + searchRes.surname,
                            value: searchRes.name + ' ' + searchRes.surname
                        };
                    } else {
                        return {
                            label: searchRes.name, value: searchRes.name
                        }
                    }
                }));
            },
        });
    }
});