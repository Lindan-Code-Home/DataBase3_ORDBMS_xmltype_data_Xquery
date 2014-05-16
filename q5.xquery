create or replace view review as select rx.*
    from REVIEWTABLE rt,
    XMLTABLE('for $x in /Review return $x'
             PASSING rt.REVIEWROW
             COLUMNS reviewer VARCHAR2(30) PATH 'Reviewer',
             rating VARCHAR2(30) PATH 'Rating'
             ) rx ;


select  review.reviewer as reviewer,count(*) as reviewNum,avg(review.rating)as rating
from  review
group by review.reviewer
order by review.reviewer asc;

drop view review;

