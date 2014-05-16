 
  create or replace view review as select rx.*
    from REVIEWTABLE rt,
    XMLTABLE('for $x in /Review return $x'
             PASSING rt.REVIEWROW
             COLUMNS title VARCHAR2(30) PATH 'Book_Title',
             rating VARCHAR2(30) PATH 'Rating'
             ) rx order by title desc;


select  review.title as bookTitle,avg(review.rating)as rating
from  review
group by review.title
order by avg(review.rating) desc;

drop view review;
