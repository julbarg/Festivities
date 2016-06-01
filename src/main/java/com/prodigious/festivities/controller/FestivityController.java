package com.prodigious.festivities.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.prodigious.festivities.dto.FestivityDTO;
import com.prodigious.festivities.model.Festivity;
import com.prodigious.festivities.service.FestivityService;
import com.prodigious.festivities.util.Util;


/** RestController FestivityController to manage festivities.
 * 
 * @author Julian Barragan */
@RestController
@RequestMapping("/festivityapi")
public class FestivityController {

   @Autowired
   private FestivityService festivityService;

   public static final String MESSAGE_WELCOME = "Welcome to Prodigious - Festity API";

   @RequestMapping(
      value = "/", method = RequestMethod.GET)
   public String hello(ModelMap model) {
      model.addAttribute("msg", "JCG Hello World!");
      return MESSAGE_WELCOME;
   }

   /** Method that allows searching all Festivities
    * GET Methood
    * 
    * @return */
   @RequestMapping(
      value = "/festivity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<Festivity>> listAll() {

      ArrayList<Festivity> listFestivity = new ArrayList<Festivity>();

      for (Festivity festivity : festivityService.findAll()) {
         listFestivity.add(festivity);
      }
      if (listFestivity.isEmpty()) {
         return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<List<Festivity>>(listFestivity, HttpStatus.OK);
   }

   /** Method that allows searching Festivities by name
    * Method GET
    * 
    * @param name Parameter that determine the searching
    * @return All of Festivities that the application has found */
   @RequestMapping(
      value = "/festivity/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<Festivity>> getFestityByName(String name) {

      List<Festivity> listFestivity = festivityService.findByName(name);
      if (listFestivity.isEmpty()) {
         return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<List<Festivity>>(listFestivity, HttpStatus.OK);
   }

   /** Method that allows searching Festivities by start date
    * Method GET
    * 
    * @param startDate Parameter that determine the searching. It's sent by URL
    * @return All of Festivities that the application has found */
   @RequestMapping(
      value = "/festivity/start_date", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<Festivity>> getFestityByStartDate(String startDate) {

      Date parseStartDate = Util.getDate(startDate);
      if (parseStartDate != null) {

         Iterable<Festivity> iterableFestivity = festivityService.findByStartDateAfter(parseStartDate);
         List<Festivity> listFestivity = new LinkedList<Festivity>();
         for (Festivity festivity : iterableFestivity) {
            listFestivity.add(festivity);
         }

         if (listFestivity.isEmpty()) {
            return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<List<Festivity>>(listFestivity, HttpStatus.OK);
      } else {
         return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
      }

   }

   /** Method that allows searching Festivities by dateRang of startDated
    * 
    * @param startDate
    * @param endDate
    * @return All of Festivities that the application has found */
   @RequestMapping(
      value = "/festivity/date_range", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<Festivity>> getFestityByDateRange(String startDate, String endDate) {

      Date parseStartDate = Util.getDate(startDate);
      Date parseEndDate = Util.getDate(endDate);
      if (parseStartDate != null && parseEndDate != null) {
         List<Festivity> festivities = festivityService.findByStartDateBetween(parseStartDate, parseEndDate);

         if (festivities.isEmpty()) {
            return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<List<Festivity>>(festivities, HttpStatus.OK);
      } else {
         return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
      }
   }

   /** Method that allows searching Festivities by name place
    * 
    * @param namePlace
    * @return All of Festivities that the application has found */
   @RequestMapping(
      value = "/festivity/name_place", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<Festivity>> getFestityByNamePlace(String namePlace) {

      List<Festivity> festivities = festivityService.findByNamePlace(namePlace);
      if (festivities.isEmpty()) {
         return new ResponseEntity<List<Festivity>>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<List<Festivity>>(festivities, HttpStatus.OK);

   }

   /** Method that allows creationg a new Festivity
    * 
    * @param festivityDTO Recive this information through JSON File sent by RequestBody
    * @return */
   @RequestMapping(
      value = "/festivity/new", method = RequestMethod.POST)
   public ResponseEntity<Festivity> newFestity(@RequestBody FestivityDTO festivityDTO) {

      try {
         Festivity festivity = new Festivity(festivityDTO);
         festivity = festivityService.save(festivity);
         return new ResponseEntity<Festivity>(festivity, HttpStatus.OK);
      } catch (Exception ex) {
         return new ResponseEntity<Festivity>(HttpStatus.BAD_REQUEST);
      }

   }

   /** Method that allows updating one Festivity that it's identified for id
    * 
    * @param id Id the element to modify
    * @param festivityDTO
    * @return */
   @RequestMapping(
      value = "/festivity/update", method = RequestMethod.POST)
   public ResponseEntity<Festivity> updateFestity(Integer id, @RequestBody FestivityDTO festivityDTO) {

      Festivity festivity = festivityService.findOne(id);
      festivity.setName(festivityDTO.getName());
      Date startDate = Util.getDate(festivityDTO.getStart());
      Date endDate = Util.getDate(festivityDTO.getEnd());
      festivity.setStartDate(startDate != null ? startDate : festivity.getStartDate());
      festivity.setEndDate(endDate != null ? endDate : festivity.getEndDate());
      festivity.setNamePlace(festivity.getNamePlace());

      festivityService.save(festivity);

      return new ResponseEntity<Festivity>(festivity, HttpStatus.OK);

   }

   /** Method that allows deleting one Festivity
    * 
    * @param id Id the element to delete
    * @return */
   @RequestMapping(
      value = "/festivity/delete", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<Void> delete(Integer id) {
      try {
         Festivity festivity = new Festivity(id);
         festivityService.delete(festivity);
      } catch (Exception ex) {
         ex.printStackTrace();
         return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<Void>(HttpStatus.OK);
   }

   @RequestMapping(
      value = "/festivity/load", method = RequestMethod.GET)
   public ResponseEntity<Void> load() {

      try {
         File fXmlFile = new File("src/main/resources/festivities.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(fXmlFile);
         doc.getDocumentElement().normalize();
         NodeList nList = doc.getElementsByTagName("festivity");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;

               FestivityDTO festivityDTO = new FestivityDTO();
               festivityDTO.setName(getTextValue(eElement, "name"));
               festivityDTO.setPlace(getTextValue(eElement, "place"));
               festivityDTO.setStart(getTextValue(eElement, "start"));
               festivityDTO.setEnd(getTextValue(eElement, "end"));

               Festivity festivity = new Festivity(festivityDTO);
               festivityService.save(festivity);

            }
         }
      } catch (Exception e) {
         return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<Void>(HttpStatus.OK);

   }

   private String getTextValue(Element ele, String tagName) {
      String textVal = null;
      NodeList nl = ele.getElementsByTagName(tagName);
      if (nl != null && nl.getLength() > 0) {
         Element el = (Element) nl.item(0);
         textVal = el.getFirstChild().getNodeValue();
      }

      return textVal;
   }

}
