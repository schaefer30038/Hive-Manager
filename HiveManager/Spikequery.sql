show databases;
use spike_exercise;

create table useraccount (
	username varchar(20),
    password varchar(20) NOT NULL,
    picture BLOB,
    email varchar(20),
    phone varchar(10),   
    primary key (username)
);

create table userapiary(
	username varchar(20),
    apiary varchar(100),
    primary key (username, apiary),
    foreign key (username) references useraccount(username) on update CASCADE on delete CASCADE
   );

create table userhive(
	username varchar(20),
    apiary varchar(100),
    hive varchar(100),
    inspection varchar(100),
    health varchar(100),
    honey varchar(100),
    queenproduction varchar (100),
    equiphive varchar (100),
    equipinven varchar(100),
    loss BIGINT,
    gain BIGINT,
    primary key (username, apiary, hive),
    foreign key (username, apiary) references userapiary(username, apiary) 
		on update CASCADE on delete CASCADE
);


create table userprefer(
	username varchar(20),
    inspection BIGINT,
    health BIGINT,
    honey BIGINT,
    queenproduction BIGINT,
    equiphive BIGINT,
    equipinven BIGINT,
    loss BIGINT,
    gain BIGINT,
    primary key (username),
    foreign key (username) references useraccount(username) 
		on update CASCADE on delete CASCADE
);

/*
This stored procedure is executed to retrieve data from userprefer for user's preference
of information of hives.
*/
delimiter $$
drop procedure if exists getPreference;
create procedure getPreference (in username varchar(20), out inspection BIGINT, 
								out health BIGINT, out honey BIGINT, out queenproduction BIGINT,
								out equiphive BIGINT, out equipinven BIGINT, out loss BIGINT, 
                                out gain BIGINT)
begin
	set inspection = (
		select up.inspection from userprefer up where up.username = username
	);
	set health = (
		select up.health from userprefer up where up.username = username
	);
    set honey = (
		select up.honey from userprefer up where up.username = username
	);
    set queenproduction = (
		select up.queenproduction from userprefer up where up.username = username
	);
    set equiphive = (
		select up.equiphive from userprefer up where up.username = username
	);
    set equipinven = (
		select up.equipinven from userprefer up where up.username = username
	);
    set loss = (
		select up.loss from userprefer up where up.username = username
	);
    set gain = (
		select up.gain from userprefer up where up.username = username
	);
end $$
delimiter ;

/*
This stored procedure is executed to update the preferrence of information of hives
*/
delimiter $$
drop procedure if exists editPreference;
create procedure editPreference(in username varchar(20),  in inspection BIGINT, 
								in health BIGINT, in honey BIGINT, in queenproduction BIGINT,
								in equiphive BIGINT, in equipinven BIGINT, in loss BIGINT, 
                                in gain BIGINT)
begin
	update userprefer up
    set up.inspection = inspection,
		up. health = health,
        up.honey = honey,
        up.queenproduction = queenproduction,
        up.equiphive = equiphive,
        up.equipinven = equipinven,
        up.loss = loss,
        up.gain = gain
	where up.username = username;
end $$
delimiter ;


/* 
Creates account only when the username input does not exist in the database server

 */
delimiter $$
drop procedure if exists createAccount;
create procedure createAccount (in username varchar(20), in password varchar(20), 
								in picture BLOB, in apiary varchar(100), 
                                in email varchar (20), in phone varchar(10), 
                                out status varchar(10))
ca: begin
	
    declare insertUsername varchar(20);
        
    set insertUsername = (
		select username
        from useraccount ua
        where ua.username = username
	);
	
    if insertUsername is NOT NULL then
		set status = "Exist";
        leave ca;
	end if;
        
    if insertUsername is NULL then
		insert into useraccount values (username, password, picture, email, phone);
        insert into userapiary values (username, apiary);
        insert into userhive values (username,apiary, "default", null, null, null, null, null, null, null, null);
        insert into userprefer values (username, 0, 0, 0, 0, 0, 0, 0, 0);
        set status = "Success";
	end if;
end $$
delimiter ;

/*
Searches account with the username

*/
delimiter $$
drop procedure if exists searchAccount;
create procedure searchAccount (in username varchar(20), in password varchar(20), 
								out result varchar (10))
pb: begin
	declare searchUsername varchar(20);
    declare searchPassword varchar(20);
    
    set searchUsername = (
		select username
        from useraccount ua
        where ua.username = username
	);
	
    if searchUsername is NULL then
		set result = "Username";
        leave pb;
	end if;
    
	set searchPassword = (
		select ua.password
        from useraccount ua
        where ua.username = username);
	
    if searchPassword != password then
		set result = "Password";
        leave pb;
	end if;
	
    set result = "Match";
end $$
delimiter ;


/*
Updates profile of the user account

*/
delimiter $$
drop procedure if exists updateProfile;
create procedure updateProfile (in username varchar(20), in picture BLOB, 
								in email varchar(20), in phone varchar(10))
begin
	
    update useraccount ua
    set ua.picture = picture,
        ua.email = email,
        ua.phone = phone
	where ua.username = username;
    	
end $$
delimiter ;


/*
Display the profile of the account
*/
delimiter $$
drop procedure if exists displayProfile;
create procedure displayProfile(in username varchar(10))
begin
	select ua.username, ua.picture, ua.email, ua.phone
    from useraccount ua
    where ua.username = username;
end $$
delimiter ;


/*
Creates a hive only when the hive is not already in the database

Returns: String status. "Exists" = the hive already exists. "Success" = Hive is successfully created
*/
delimiter $$
drop procedure if exists createHive;
create procedure createHive(in username varchar(20), in apiary varchar(100), 
							in hive varchar (100), in inspection varchar(100),
							in health varchar(100), in honey varchar(100), 
                            in queenproduction varchar(100), in equiphive varchar(100),
                            in equipinven varchar(100), in loss BIGINT, in gain BIGINT, 
                            out status varchar(10))
ch: begin
	declare exist varchar(100);
	declare findHive varchar(100);
    set exist = (
		select ua.username
        from userapiary ua
        where ua.username = username
        and ua.apiary = apiary);
    if exist is NULL then
		set status = "DNEApiary";
        leave ch;
	end if;
    set findHive = (
		select uh.hive
        from userhive uh
        where uh.hive = hive
			and uh.apiary = apiary
            and uh.username = username);
	if findHive is NOT NULL then
		set status = "Exists";
        leave ch;
	end if;
    insert into userhive values (username, apiary, hive, inspection, health, honey, 
								queenproduction, equiphive, equipinven, loss, gain);
    set status = "Success";
end $$
delimiter ;

/*
Display the overview of hive that is owned by the username
*/
delimiter $$
drop procedure if exists displayHive;
create procedure displayHive(in username varchar(20))
begin
	select *
    from userhive uh
    where uh.username = username;
end $$
delimiter ;


/*
Updates information of the hive
Must follow the input order
Param: Hive name, Results of inspection, Health, Honey stores, Queen production, 
		Equipment on the hive, Equipment in inventory, losses, gains
*/
delimiter $$
drop procedure if exists updateHive;
create procedure updateHive(in username varchar(20), in apiary varchar(100), 
							in oldhive varchar(100), in newhive varchar(100), 
                            in inspection varchar(100), in health varchar(100), 
                            in honey varchar(100), in queenproduction varchar(100), 
                            in equiphive varchar(100), in equipinven varchar (100), 
                            in loss BIGINT, in gain BIGINT, out status varchar(10))
proc: begin
    declare exist varchar(100);
    if oldhive <> newhive then
	    set exist = (
			select uh.hive
			from userhive uh
			where uh.username = username
				and uh.hive = newhive);
    
		if exist is NOT NULL then
			set status = "Exist";
			leave proc;
		end if;
    
		update userhive uh
		set uh.hive = newhive,
			uh.inspection = inspection,
			uh.health = health,
			uh.honey = honey,
			uh.queenproduction = queenproduction,
			uh.equiphive = equiphive,
			uh.equipinven = equipinven,
			uh.loss = loss,
			uh.gain = gain
		where uh.username = username
			and uh.apiary = apiary
			and uh.hive = oldhive;
	
		set status = "Success";
        leave proc;
	end if;
    
    update userhive uh
		set uh.inspection = inspection,
			uh.health = health,
			uh.honey = honey,
			uh.queenproduction = queenproduction,
			uh.equiphive = equiphive,
			uh.equipinven = equipinven,
			uh.loss = loss,
			uh.gain = gain
		where uh.username = username
			and uh.apiary = apiary
			and uh.hive = oldhive;
	set status = "success";
end $$
delimiter ;


/*
Deletes information of hive
*/
delimiter $$
drop procedure if exists deleteHive;
create procedure deleteHive (in username varchar(20), in apiary varchar(100), 
								in hive varchar(100), out status varchar(10))
dh: begin
	
    declare exist varchar(10);
	set exist =(
		select ua.username
        from userhive ua
        where ua.username = username
        and ua.apiary = apiary
        and ua.hive = hive
	);
    if exist is NULL then
		set status = "DNE";
        leave dh;
	end if;
    
    delete from userhive uh
		where uh.username = username
        and uh.apiary = apiary
        and uh.hive = hive;
	set status = "Success";
end $$
delimiter ;

/*
This stored procedure is executed to create a new apary address that does not 
exist in the database
*/
delimiter $$
drop procedure if exists createApiary;
create procedure createApiary (in username varchar(20), in apiary varchar(100), 
								out status varchar(10))
ca: begin

	
    declare existap varchar(100);
 
    set existap = (
		select up.apiary
        from userapiary up
        where up.username = username
			and up.apiary = apiary
	);
        
	if existap is NOT NULL then
		set status = "Exist";
        leave ca;
	end if;
    
    insert into userapiary values (username, apiary);
    call createHive(username, apiary, "default", null, null, null, null, null, null, 0, 0, @test);
    set status = "Success";
end $$
delimiter ;

/*
This stored procedure is executed to change the name of the apiary address
*/
delimiter $$
drop procedure if exists updateApiary;
create procedure updateApiary(in username varchar(20), in oldapiary varchar(100), 
								in newapiary varchar(100), out status varchar(10))
ua: begin
	declare exist varchar(10);
    
    set exist = (
		select apiary
        from userapiary ua
        where ua.username = username
			and ua.apiary = oldapiary
	);
    
    if exist is NULL then
		set status = "DNE";
        leave ua;
	end if;
    
    set exist =(
		select apiary
        from userapiary ua
        where ua.username = username
			and ua.apiary = newapiary
	);
    if exist is NOT NULL then
		set status = "Exist";
        leave ua;
	end if;
    
    update userapiary ua
    set ua.apiary = newapiary
    where ua.username = username
		and ua.apiary = oldapiary;
	
    set status = "Success";
end $$

/*
This stored procedure is used to delete a specific apiary address with the username and the apiary address 
*/
delimiter $$
drop procedure if exists deleteApiary;
create procedure deleteApiary (in username varchar(20), in apiary varchar(100), 
							   out status varchar(10))
da: begin
	declare exist varchar(10);
    
    set exist = (
		select ua.apiary
        from userapiary ua
        where ua.username = username
			and ua.apiary = apiary
	);
    
    if exist is NULL then
		set status = "DNE";
        leave da;
	end if;
    
    delete from userapiary ua where ua.username = username and ua.apiary = apiary;
	set status = "Success";	
        
end $$
delimiter ;

/*
This stored procedure is used to display full information of a specific hive chosen by the user
*/
delimiter $$
drop procedure if exists displaySpecificHive;
create procedure displaySpecificHive(in username varchar(20), in apiary varchar(100), 
					in hive varchar(100), out status varchar(10))
proc: begin
	declare exist varchar(10);
    
    set exist = (
		select uh.hive
        from userhive uh
        where uh.username = username
			and uh.apiary = apiary
            and uh.hive = hive
	);
    
    if exist is NULL then
		set status = "DNE";
        leave proc;
	end if;
    
    select *
    from userhive uh
    where uh.username = username
		and uh.apiary = apiary
        and uh.hive = hive;
        
	set status = "Success";
end $$
delimiter ;

/*
The stored procedure executets query to display all apiary addreses owned by the user
*/
delimiter $$
drop procedure if exists displayApiary;
create procedure displayApiary (in username varchar(20))
begin
	select * from userapiary ua where ua.username = username;
    
end $$
delimiter ;

