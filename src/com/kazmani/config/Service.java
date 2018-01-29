package com.kazmani.config;

import java.io.InputStream; 
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.kazmani.access.*;
import com.kazmani.account.Account;
import com.kazmani.account.AccountDao;
import com.kazmani.admin.*;
import com.kazmani.bookings.*;
import com.kazmani.client.*;
import com.kazmani.contact.Contact;
import com.kazmani.contact.ContactDao;
import com.kazmani.credentials.*;
import com.kazmani.massage.*;
import com.kazmani.messaging.Mail;
import com.kazmani.messaging.MailDao;
import com.kazmani.payment.*;
import com.kazmani.photo.*;
import com.kazmani.qualification.*;
import com.kazmani.therapist.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.*;

@Path("/api/v1")
public class Service {

    Gson gson = new Gson();
    LoginDao loginService = new LoginDao();
    ClientDao clientService = new ClientDao();
    TherapistDao therapistService = new TherapistDao();
    QualificationDao qualificationService = new QualificationDao();
    PhotoDao photoService = new PhotoDao();
    PaymentDao paymentService = new PaymentDao();
    AdminDao adminService = new AdminDao();
    BookingDao bookingService = new BookingDao();
    MassageDao massageService = new MassageDao();
    ContactDao contactService = new ContactDao();
    AccountDao accountService = new AccountDao();
    MailDao mailService = new MailDao();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String doLogin(String jSonCredentials) throws SQLException {

	Credentials credentials = gson.fromJson(jSonCredentials,
		Credentials.class);
	Login loginResponse = loginService.authenticate(credentials);
	return gson.toJson(loginResponse);
    }

    /**
     * This section of the code simply helps to do every operation related to
     * therapist module
     */
    @POST
    @Path("/therapist/addtherapist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String signUp(String jsTherapistString) throws SQLException {
	// addrDesc, addrType, addrState, addrLocalGvt, status, profileText
	Therapist thr = gson.fromJson(jsTherapistString, Therapist.class);
	return gson.toJson(therapistService.addNewTherapist(thr));
    }

    @GET
    @Path("/therapist/checkstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkStatus(@QueryParam("phoneNumber") String phoneNumber) {
	Therapist therapistStatus = therapistService.checkStatus(phoneNumber);
	return gson.toJson(therapistStatus);
    }

    @GET
    @Path("/therapist/getbynumber")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTherapist(@QueryParam("phoneNumber") String phoneNumber) {
	return gson.toJson(therapistService.getTherapistById(phoneNumber));
    }

    @POST
    @Path("/therapist/addqualifications")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addQualifications(String jSonQualification)
	    throws SQLException {
	Qualification q = gson.fromJson(jSonQualification, Qualification.class);
	return gson.toJson(qualificationService.addQualification(q));
    }

    @PUT
    @Path("/therapist/editprofiletext")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editProfileText(
	    @QueryParam("phoneNumber") String phoneNumber,
	    @QueryParam("newtext") String newProfileText) throws SQLException {
	Therapist therapistProfile = therapistService.editProfileText(
		phoneNumber, newProfileText);
	return gson.toJson(therapistProfile);
    }

    @POST
    @Path("/therapist/addaccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createAccount(String jSonAccont) throws SQLException {
	Account account = gson.fromJson(jSonAccont, Account.class);
	return gson.toJson(accountService.createAccount(account));
    }

    @PUT
    @Path("/therapist/editaccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public String editAccount(String jSonAccont) throws SQLException {
	Account account = gson.fromJson(jSonAccont, Account.class);
	return gson.toJson(accountService.editAccountByPhoneNumber(account));
    }

    @GET
    @Path("/therapist/getaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccountDetails(
	    @QueryParam("phoneNumber") String phoneNumber) {
	return gson.toJson(accountService.getAccountByPhoneNumber(phoneNumber));
    }

    /**
     * This section of the controller does everything with client related
     * operations
     */
    @POST
    @Path("/client/addclient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addNewClient(String jsonClient) throws SQLException {
	Client client = gson.fromJson(jsonClient, Client.class);
	return gson.toJson(clientService.addNewClient(client));
    }

    @GET
    @Path("client/getclient")
    @Produces(MediaType.APPLICATION_JSON)
    public String getClientByPhoneNumber(
	    @QueryParam("phoneNumber") String phoneNumber) {
	Client client = clientService.getClientByPhoneNumber(phoneNumber);
	return gson.toJson(client);
    }

    // gets payment list
    @GET
    @Path("client/getpaylist")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPaymentList(@QueryParam("payerId") String payerId) {
	ArrayList<PaymentData> paymentList = paymentService
		.getPaymentsListById(payerId);
	return gson.toJson(paymentList);
    }

    // keep details of payment made for massage service
    @POST
    @Path("client/pay")
    @Consumes(MediaType.APPLICATION_JSON)
    public String payForMassage(String paymentJsonString) throws SQLException {

	PaymentData paymentData = gson.fromJson(paymentJsonString,
		PaymentData.class);

	PaymentData paymentResponse = paymentService.makePayment(
		paymentData.getSenderId(), paymentData.getSenderCardNumber(),
		paymentData.getSenderCardExpirationDate(),
		paymentData.getSenderCardVerificationCode(),
		paymentData.getBeneficiaryBank(),
		paymentData.getBeneficiaryAcctNum(),
		paymentData.getBeneficiaryPhoneNumber(),
		paymentData.getAmount(), paymentData.getTransactionDate(),
		paymentData.getPaymentDescription());

	return gson.toJson(paymentResponse);
    }

    /* The section of code below handles everything with users'contact */
    @POST
    @Path("/contact/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addContact(String jsonContact) throws SQLException {
	Contact newContact = gson.fromJson(jsonContact, Contact.class);
	return gson.toJson(contactService.addNewContact(newContact));
    }

    @PUT
    @Path("/contact/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editContact(String jsonContact) throws SQLException {
	Contact contact = gson.fromJson(jsonContact, Contact.class);
	return gson.toJson(contactService.editContactById(contact));
    }

    @DELETE
    @Path("/contact/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteContact(String jsonIdString) throws SQLException {
	Contact contact = gson.fromJson(jsonIdString, Contact.class);
	return gson.toJson(contactService.deleteContactById(contact.getId()));
    }

    @GET
    @Path("/contact/fetch")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContact(@QueryParam("id") String phoneOrEmail) {
	Contact contact = contactService.getContactById(phoneOrEmail);
	return gson.toJson(contact);
    }

    // request for massage
    @POST
    @Path("booking/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public String requestForMassage(String jsonBooking) throws SQLException {
	Booking bk = gson.fromJson(jsonBooking, Booking.class);

	return gson.toJson(bookingService.bookMassage(bk));
    }

    // request for massage
    @GET
    @Path("booking/last")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getLastBookingById(@QueryParam("phoneNumber") String phoneNumber) {
	return gson.toJson(bookingService.getLastBookingByClientId(phoneNumber));
    }

    // request for massage
    @GET
    @Path("booking/all")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllbookingsById(@QueryParam("phoneNumber") String phoneNumber) {
         return gson.toJson(bookingService.getAllBookingsByClientId(phoneNumber));
    }

    // handling mail messaging
    @POST
    @Path("/messaging/mail")
    @Consumes(MediaType.APPLICATION_JSON)
    public String sendMail(String jsonMail) {
	Mail email = gson.fromJson(jsonMail, Mail.class);
	return gson.toJson(mailService.sendMail(email));
    }

    // upload profile image for users
    @POST
    @Path("/uploadphoto")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadImage(
	    @FormDataParam("image_id2") FormDataBodyPart image_id,
	    @FormDataParam("imageURI") InputStream image_File) {

	String imgId = image_id.getValue();
	int imagId = Integer.parseInt(imgId);
	Photo imageData = photoService.insertImage(imagId, image_File);
	return gson.toJson(imageData);
    }

}

/*
 * Personal Notes: GET,,HEAD,OPTIONS and TRACE methods NEVER change the resource
 * state on server.They are purely for resource retrieval purposes(READ)..So
 * invoking multiple requests will NEVER have any WRITE operation on the
 * server...Hence,they are all idempotent!
 * 
 * PUT is idempotent...making multiple request outputs same result as a single
 * request PUT is used for either resource create operations or update
 * operations i.e PUT does update when the URI being pointed to is of a resource
 * that already exists and creates when the resource does not already exists.
 * But Generally,in practice,always use PUT for UPDATE operations.
 * 
 * DELETE is idempotent also...How? when a delete request is made multiple
 * times,a change is effected on the server the first time and since such
 * resource wouldnt be available anymore, the results of the remaining N-1
 * requests is same as the first as no further change will be effected
 * 
 * POST is NOT idempotent...making multiple requests is never same as the result
 * of a single request every call to POST method attempts create a new resource
 * on the server.
 */
