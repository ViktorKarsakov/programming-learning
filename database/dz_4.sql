-- Запрос 1
select t.surname, g.name 
from groups as g, teachers as t;

-- Запрос 2
select distinct f.name
from faculties as f
join departments as d on d.faculty_id = f.id
where d.financing > f.financing;

-- Запрос 3
select c.surname, g.name
from curators as c
join groupscurators as gc on gc.curator_id = c.id
join groups as g on gc.group_id = g.id;

-- Запрос 4
select t.name, t.surname
from teachers as t
join lectures as l on l.teacher_id = t.id
join groupslectures as gl on gl.lecture_id = l.id
join groups as g on gl.group_id = g.id
where g.name = 'P107';

-- Запрос 5
select distinct t.surname, f.name
from teachers as t
join lectures as l on l.teacher_id = t.id
join groupslectures as gl on gl.lecture_id = l.id
join groups as g on gl.group_id = g.id
join departments as d on g.department_id = d.id
join faculties as f on d.faculty_id = f.id;

-- Запрос 6
select d.name, g.name
from departments as d
join groups as g on g.department_id = d.id;

-- Запрос 7
select s.name
from subjects as s
join lectures as l on l.subject_id = s.id
join teachers as t on l.teacher_id = t.id
where t.surname = 'Adams';

-- Запрос 8
select d.name
from departments as d
join groups as g on g.department_id = d.id
join groupslectures as gl on gl.group_id = g.id
join lectures as l on gl.lecture_id = l.id
join subjects as s on l.subject_id = s.id
where s.name = 'Databases';

-- Запрос 9
select g.name 
from groups as g
join departments as d on g.department_id = d.id
join faculties as f on d.faculty_id = f.id
where f.name = 'Computer Science';

-- Запрос 10
select g.name, f.name
from groups as g
join departments as d on g.department_id = d.id
join faculties as f on d.faculty_id = f.id
where g.year = 5;

-- Запрос 11
select distinct t.name || ' ' || t.surname,
g.name
from lectures as l
join teachers as t on l.teacher_id = t.id
join groupslectures as gl on gl.lecture_id = l.id
join groups as g on gl.group_id = g.id
where l.lectureroom = 'B103';