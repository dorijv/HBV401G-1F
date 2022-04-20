# HBV401G-1F

Hópur 1F:

Alda Guðný Pálsdóttir : agp15@hi.is

Ármann Schevander : ars90@hi.is

Halldór Jens Vilhjálmsson : hjv6@hi.is

Hrólfur Júlíusson : hrolfur@hi.is

<H1><Strong>Leiðbeiningar</Strong></H1>

Til þess að setja upp á tölvunum ykkar þurfiði að hafa global library sem er þá JavaFX lib.
Í framhaldinu skoðið þið dependency en þar þurfiði mögulega að taka út JavaFX library-ið sem ég setti inn og láta
inn ykkar eigið lib. ATH. það þarf ekki að gera neitt í run configurations því module-info.java ætti að sjá um það.

Varðandi gagnagrunn fylgja leiðbeiningar í db.Setup.java skjalinu

ATH JavaDoc í /Documentation/ möppunni. Opnið index.html

<H1><Strong>NOTE</Strong></H1>
We've removed most unutilized classes but in case the following should be looked at:
FlightController - Main function
Booking - Object
Flight - Object
NotSecureHash - For implementing booking number, as requested by T group
BookingController - Controller made for test interface

Following classes were omitted:
Customer and CustomerController - Group T requested that they implement it
BookingController - Implementing the few methods in FlightController was simpler.
Payment - Since we did not implement anything related to customer, this seemed obscure
            due to needing the customer base.

About running the program:
1F.db is basically run-ready, you will need to figure out how to implement JavaFX on your system.
the test folder needs to be excluded in project structure.