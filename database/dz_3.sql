-- Запрос 1
select name, financing, id from departments
order by id desc;

-- Запрос 2
select g.name, g.rating
from groups as g;

-- Запрос 3
select 
	surname,
	round(salary / premium * 100, 2) as salary_to_premium_pct,
	round(salary / (salary + premium) * 100, 2) as salary_to_total_pct
from teachers;

-- Запрос 4
select
	format(
		'The dean of faculty %s is %s.',
		name,
		dean
	) as faculty_dean_info
from faculties;

-- Запрос 5
select surname from teachers
where is_professor = true and salary > 1050;

-- Запрос 6
select name from departments
where financing < 11000 or financing > 25000;

-- Запрос 7
select name from faculties
where not name = 'Computer Science';

-- Запрос 8
select surname, position from teachers
where is_professor = false;

-- Запрос 9
select surname, position, salary, premium from teachers
where is_assistant = true and premium between 160 and 550;

-- Запрос 10
select surname, salary from teachers
where is_assistant = true;

-- Запрос 11
select surname, position from teachers
where employment_date < '2000-01-01';

-- Запрос 12
select name as "Name of Department" from departments
where name > 'Biology'
order by name;

-- Запрос 13
select surname from teachers
where is_assistant = true and (salary + premium) <= 1200;

-- Запрос 14
select name from groups
where year = 5 and rating between 2 and 4;

-- Запрос 15
select surname from teachers
where is_assistant = true and (salary < 550 or premium < 200);