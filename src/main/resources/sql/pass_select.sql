select * from t_common_pass
/*IF SF.isNotEmpty(value)*/
where password = /*value*/1
/*END*/
;