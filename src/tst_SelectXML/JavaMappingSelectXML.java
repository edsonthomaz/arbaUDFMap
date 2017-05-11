package tst_SelectXML;

import java.io.ByteArrayInputStream;
import java.text.Normalizer;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JavaMappingSelectXML {

	public static void main(String args[] ) {
		selectXMLTaxDiscount();
		//selectXMLArguments();
		//selectXML_PARID();
		//selectXML_XPath();
		//System.out.println( Test.replaceSpecialChar("!@#$%¨&*()teste ção ü{}^?/°.asdf0()¨//°") );
		//System.out.println( Test.splitByValue(1, "testtest$12345", "$") );
		
	}
	
	public static void selectXML_PARID(){
		
		String[] xmllist = {"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Contact role=\"shipFrom\"><Name xml:lang=\"en\">BBBBBBBBB</Name><PostalAddress><Street>teste</Street><City>teste</City><State/><PostalCode>00000000</PostalCode><Country isoCountryCode=\"BR\">Brazil</Country></PostalAddress><IdReference identifier=\"AAAAA\" domain=\"departmentName\"/></Contact>", 
							"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Contact role=\"shipTo\"><Name xml:lang=\"en\">SÃO PAULO - ESCRITÓRIO</Name><PostalAddress><DeliverTo>XXXXX</DeliverTo><Street>AAAAAAA</Street><City>SAO PAULO</City><State>SP</State><PostalCode>123123</PostalCode><Country isoCountryCode=\"BR\">Brazil</Country></PostalAddress><IdReference identifier=\"\" domain=\"departmentName\"/></Contact>"}; 
		String keyfield = "Contact";
		String keyfieldAttributeName = "role";
		String keyfieldAttributeValue = "shipFrom";
		String itemId = "IdReference";
		String itemIdAttributeName = "domain";
		String itemIdAttributeValue = "departmentName";
		String returnAttributeName = "identifier";
		
		try {
			StringBuffer sb = new StringBuffer();
			for (String xml : xmllist) {
				if(xml.trim().length()>0){
					DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document document = docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
					NodeList tagslist = document.getElementsByTagName(keyfield);

					for(int i = 0; i< tagslist.getLength();i++) {
						Node node = tagslist.item(i);
						Element idElement = (Element)node;
						String kfAttrValue = idElement.getAttribute(keyfieldAttributeName);
						if (keyfieldAttributeValue.equals(kfAttrValue)) {
							NodeList itemList = idElement.getElementsByTagName(itemId);
							for (int j = 0; j < itemList.getLength(); j++) {
								Element itemElement = (Element)itemList.item(j);
								String itemAttrValue = itemElement.getAttribute(itemIdAttributeName);
								if (itemIdAttributeValue.equals(itemAttrValue)) {
									sb.append(itemElement.getAttribute(returnAttributeName));
								}
							}
						}
					}
					//result.addValue( sb.toString());
					System.out.println(sb.toString());
					sb = new StringBuffer();
				}
			}
		} catch(Exception e){
			//throw new StreamTransformationException("Unable to parse xml", e);
			System.out.println("Erro");
		}
	}
	
	public static void selectXMLTaxDiscount() {
		String[] xmllist = {"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><E1EDP01 SEGMENT=\"1\"><POSEX>00001</POSEX><ACTION>001</ACTION><PSTYP>0</PSTYP><MENGE>2.000</MENGE><MENEE>EA</MENEE><BMNG2>2.000</BMNG2><PMENE>EA</PMENE><VPREI>900.00</VPREI><PEINH>1</PEINH><NETWR>1800.00</NETWR><ANETW>0.00</ANETW><MATKL>75A</MATKL><BPUMN>1</BPUMN><BPUMZ>1</BPUMZ><WERKS>14</WERKS><E1EDP04 SEGMENT=\"1\"><MWSKZ>IPI</MWSKZ><MSATZ>15.00</MSATZ><MWSBT>340.48</MWSBT><TXJCD>SP</TXJCD><KTEXT>IPI não dedutível</KTEXT></E1EDP04><E1EDP04 SEGMENT=\"1\"><MWSKZ>ICMS</MWSKZ><MSATZ>18.00</MSATZ><MWSBT>469.86</MWSBT><TXJCD>SP</TXJCD><KTEXT>ICMS não-dedutível</KTEXT></E1EDP04><E1EDP05 SEGMENT=\"1\"><ALCKZ>+</ALCKZ><KSCHL>PBXX</KSCHL><KOTXT>Preço bruto</KOTXT><BETRG>1800</BETRG><KRATE>900</KRATE><UPRBS>       1</UPRBS><MEAUN>EA</MEAUN><KOEIN>BRL</KOEIN></E1EDP05><E1EDP20 SEGMENT=\"1\"><WMENG>2.000</WMENG><AMENG>0.000</AMENG><EDATU>20170127</EDATU></E1EDP20><E1EDP19 SEGMENT=\"1\"><QUALF>001</QUALF><IDTNR>000000000008000116</IDTNR><KTEXT>ALFINETE MAPAS N.1 C/50 AMARELO</KTEXT></E1EDP19><E1EDP35 SEGMENT=\"1\"><CUSADD>NCM</CUSADD><CUSADD_BEZ>73194000001</CUSADD_BEZ></E1EDP35><E1EDP35 SEGMENT=\"1\"><QUALZ>NOP</QUALZ><CUSADD>2</CUSADD><CUSADD_BEZ>Consumo</CUSADD_BEZ></E1EDP35><E1EDPT1 SEGMENT=\"1\"><TDID>F01</TDID><TSSPRAS>P</TSSPRAS><TSSPRAS_ISO>PT</TSSPRAS_ISO><E1EDPT2 SEGMENT=\"1\"><TDLINE>ALFINETE MAPAS NUMERO 1 CAIXA COM 50 UNIDADES AMARELO - REF. ACC,</TDLINE><TDFORMAT>*</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>BACCHI, CHAPARRAU</TDLINE><TDFORMAT>/</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDFORMAT>/</TDFORMAT></E1EDPT2></E1EDPT1></E1EDP01>",
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><E1EDP01 SEGMENT=\"1\"><POSEX>00002</POSEX><ACTION>001</ACTION><PSTYP>0</PSTYP><MENGE>4.000</MENGE><MENEE>EA</MENEE><BMNG2>4.000</BMNG2><PMENE>EA</PMENE><VPREI>10.00</VPREI><PEINH>1</PEINH><NETWR>40.00</NETWR><ANETW>0.00</ANETW><MATKL>75A</MATKL><BPUMN>1</BPUMN><BPUMZ>1</BPUMZ><WERKS>14</WERKS><E1EDP04 SEGMENT=\"1\"><MWSKZ>ICMS</MWSKZ><MSATZ>18.00</MSATZ><MWSBT>8.78</MWSBT><TXJCD>SP</TXJCD><KTEXT>ICMS não-dedutível</KTEXT></E1EDP04><E1EDP05 SEGMENT=\"1\"><ALCKZ>+</ALCKZ><KSCHL>PBXX</KSCHL><KOTXT>Preço bruto</KOTXT><BETRG>40</BETRG><KRATE>10</KRATE><UPRBS>       1</UPRBS><MEAUN>EA</MEAUN><KOEIN>BRL</KOEIN></E1EDP05><E1EDP20 SEGMENT=\"1\"><WMENG>4.000</WMENG><AMENG>0.000</AMENG><EDATU>20170127</EDATU></E1EDP20><E1EDP19 SEGMENT=\"1\"><QUALF>001</QUALF><IDTNR>000000000008000113</IDTNR><KTEXT>AGENDA TELEFONICA TILIBRA</KTEXT></E1EDP19><E1EDP35 SEGMENT=\"1\"><CUSADD>NCM</CUSADD><CUSADD_BEZ>96091000001</CUSADD_BEZ></E1EDP35><E1EDP35 SEGMENT=\"1\"><QUALZ>NOP</QUALZ><CUSADD>2</CUSADD><CUSADD_BEZ>Consumo</CUSADD_BEZ></E1EDP35><E1EDPT1 SEGMENT=\"1\"><TDID>F01</TDID><TSSPRAS>P</TSSPRAS><TSSPRAS_ISO>PT</TSSPRAS_ISO><E1EDPT2 SEGMENT=\"1\"><TDLINE>Item 002 XAGENDA TELEFONICA TILIBRA               0</TDLINE><TDFORMAT>*</TDFORMAT></E1EDPT2></E1EDPT1></E1EDP01>",
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><E1EDP01 SEGMENT=\"1\"><POSEX>00003</POSEX><ACTION>001</ACTION><PSTYP>0</PSTYP><MENGE>5.000</MENGE><MENEE>EA</MENEE><BMNG2>5.000</BMNG2><PMENE>EA</PMENE><VPREI>12.00</VPREI><PEINH>1</PEINH><NETWR>60.00</NETWR><ANETW>0.00</ANETW><MATKL>75A</MATKL><BPUMN>1</BPUMN><BPUMZ>1</BPUMZ><WERKS>14</WERKS><E1EDP05 SEGMENT=\"1\"><ALCKZ>+</ALCKZ><KSCHL>PBXX</KSCHL><KOTXT>Preço bruto</KOTXT><BETRG>60</BETRG><KRATE>12</KRATE><UPRBS>       1</UPRBS><MEAUN>EA</MEAUN><KOEIN>BRL</KOEIN></E1EDP05><E1EDP20 SEGMENT=\"1\"><WMENG>5.000</WMENG><AMENG>0.000</AMENG><EDATU>20170127</EDATU></E1EDP20><E1EDP19 SEGMENT=\"1\"><QUALF>001</QUALF><IDTNR>000000000008000112</IDTNR><KTEXT>AGENDA JOVEM GAPP ANUAL 7200</KTEXT></E1EDP19><E1EDP35 SEGMENT=\"1\"><CUSADD>NCM</CUSADD><CUSADD_BEZ>96091000001</CUSADD_BEZ></E1EDP35><E1EDP35 SEGMENT=\"1\"><QUALZ>NOP</QUALZ><CUSADD>2</CUSADD><CUSADD_BEZ>Consumo</CUSADD_BEZ></E1EDP35><E1EDPT1 SEGMENT=\"1\"><TDID>F01</TDID><TSSPRAS>P</TSSPRAS><TSSPRAS_ISO>PT</TSSPRAS_ISO><E1EDPT2 SEGMENT=\"1\"><TDLINE>ITEM 003 X  AGENDA JOVEM GAPP ANUAL 7200            0X</TDLINE><TDFORMAT>*</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>XX</TDLINE><TDFORMAT>*</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>X</TDLINE><TDFORMAT>*</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>X</TDLINE><TDFORMAT>*</TDFORMAT></E1EDPT2></E1EDPT1></E1EDP01>"}; 
		String[] keyvaluelist = {"00001", "00003"};
		String keyfield = "POSEX";
		String[] totalTax = {"890.34", "18.78"}; 
		String categoryField = "MWSKZ"; 
		String categoryExceptions = "IPI,ICMS";
		String taxField = "MWSBT";
		
		try {
			String[] categoryException = categoryExceptions.split(",");
			StringBuffer sb = new StringBuffer();
			double netTax = 0;
			double totalDiscountTax = 0;
			for (int h = 0; h < xmllist.length; h++) {		
			//for (String xml : xmllist) {
				String xml = xmllist[h];
				if(xml.trim().length()>0){
					DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document document = docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
					NodeList tagslist = document.getElementsByTagName(keyfield);

					for(int i = 0; i< tagslist.getLength();i++) {
						Node node = tagslist.item(i);
						String nodename = node.getFirstChild().getNodeValue();
						for (String keyvalue : keyvaluelist) {

							if(nodename.equals(keyvalue)){
								//find the text Tag
								Element itemNode = (Element)node.getParentNode();
								NodeList textList = itemNode.getElementsByTagName(categoryField);
								for (int j = 0; j < textList.getLength(); j++) {
									Node textNode = textList.item(j);
									String textName = textNode.getFirstChild().getNodeValue();
									
									if(Arrays.asList(categoryException).contains(textName)){
										//add the content of the result field to the output
										//look from the parrent node
										Element parrentNode = (Element)textNode.getParentNode();
										NodeList resultList = parrentNode.getElementsByTagName(taxField);
										for(int k = 0; k< resultList.getLength();k++) {
											Node resultnode = resultList.item(k);
											String strTaxValue = resultnode.getFirstChild().getNodeValue();
											try {
												totalDiscountTax = totalDiscountTax + Double.parseDouble(strTaxValue);
											} catch (Exception ex) {
												//Nothing
											}													
										}
										
									}
								}
								try {
									netTax = Double.parseDouble(totalTax[h]) - totalDiscountTax;
								} catch (Exception ex) {
									netTax = 0;
								}
								
								//result.addValue( String.valueOf(netTax));
								System.out.println(String.valueOf(netTax));
								totalDiscountTax = 0;
								netTax = 0;						
							}
						}
					}					
				}
			}
		} catch(Exception e){
			//throw new StreamTransformationException("Unable to parse xml", e);
			System.out.println("Erro");
		}
						
	}
	
	public static void selectXML(){
		
		String[] xmllist = {"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><E1EDP01 SEGMENT=\"1\"><POSEX>00001</POSEX><ACTION>001</ACTION><PSTYP>9</PSTYP><MENGE>             1</MENGE><MENEE>EA</MENEE><BMNG2>1.000</BMNG2><PMENE>EA</PMENE><VPREI>0.00</VPREI><PEINH>1</PEINH><NETWR>0.00</NETWR><ANETW>76500.00</ANETW><MATKL>SERV00002</MATKL><BPUMN>1</BPUMN><BPUMZ>1</BPUMZ><WERKS>14</WERKS><E1EDP05 SEGMENT=\"1\"><ALCKZ>+</ALCKZ><KSCHL>PBXX</KSCHL><KOTXT>Preço bruto</KOTXT><BETRG>76500</BETRG><KRATE>76500</KRATE><UPRBS>       1</UPRBS><MEAUN>EA</MEAUN><KOEIN>BRL</KOEIN></E1EDP05><E1EDP20 SEGMENT=\"1\"><WMENG>1.000</WMENG><AMENG>0.000</AMENG><EDATU>20161217</EDATU></E1EDP20><E1EDP19 SEGMENT=\"1\"><QUALF>001</QUALF><IDTNR>000000000008376288</IDTNR><KTEXT>JBM - SECONDMENT</KTEXT></E1EDP19><E1EDP35 SEGMENT=\"1\"><CUSADD>NCM</CUSADD><CUSADD_BEZ>LC116-17.14</CUSADD_BEZ></E1EDP35><E1EDP35 SEGMENT=\"1\"><QUALZ>NOP</QUALZ><CUSADD>1</CUSADD><CUSADD_BEZ>Industrialização</CUSADD_BEZ></E1EDP35><E1EDPT1 SEGMENT=\"1\"><TDID>F01</TDID><TSSPRAS>P</TSSPRAS><TSSPRAS_ISO>PT</TSSPRAS_ISO><E1EDPT2 SEGMENT=\"1\"><TDFORMAT>*</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>1111111Escopo: RC específica para contratação de Secondments do escritório</TDLINE><TDFORMAT>*</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>1111111ASDFASFDASDFpara alocação de advogado e estagiário, para prestação</TDLINE></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>111111de serviços na CSN, referente a atendimento e auxílio junto ao Projeto</TDLINE></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>11111de Auditoria de Valores/Depósitos.</TDLINE></E1EDPT2></E1EDPT1><E1EDPT1 SEGMENT=\"1\"><TDID>ARB</TDID><E1EDPT2 SEGMENT=\"1\"><TDLINE>true</TDLINE></E1EDPT2></E1EDPT1><E1EDC01 SEGMENT=\"1\"><SGTYP>002</SGTYP><POSEX>0000000000</POSEX><RANG>0</RANG><MENGE>             0</MENGE><CURCY>BRL</CURCY><AUSGB>0000</AUSGB><FRPOS>    1</FRPOS><PERNR>00000000</PERNR><STELL>00000000</STELL><E1EDC02 SEGMENT=\"1\"><QUALF>001</QUALF><XLINE>0000000000</XLINE></E1EDC02></E1EDC01><E1EDC01 SEGMENT=\"1\"><SGTYP>002</SGTYP><POSEX>5000100010</POSEX><RANG>0</RANG><MATKL>SERV00002</MATKL><MENGE>             0</MENGE><MENEE>VB</MENEE><PMENE>VB</PMENE><VPREI>8500</VPREI><PEINH>1</PEINH><NETWR>51000</NETWR><CURCY>BRL</CURCY><PREIS>8500</PREIS><AUSGB>0000</AUSGB><KTXT1>HONORÁRIOS</KTXT1><PERNR>00000000</PERNR><STELL>00000000</STELL><E1EDC02 SEGMENT=\"1\"><QUALF>001</QUALF><XLINE>5000100010</XLINE></E1EDC02><E1EDC02 SEGMENT=\"1\"><QUALF>003</QUALF><XLINE>0000000000</XLINE></E1EDC02><E1EDCT1 SEGMENT=\"1\"><TDID>ARB</TDID><E1EDCT2 SEGMENT=\"1\"><TDLINE>00001</TDLINE></E1EDCT2></E1EDCT1><E1EDCT1 SEGMENT=\"1\"><TDID>PACK</TDID><E1EDCT2 SEGMENT=\"1\"><TDLINE>0-0000000010</TDLINE></E1EDCT2></E1EDCT1></E1EDC01><E1EDC01 SEGMENT=\"1\"><SGTYP>002</SGTYP><POSEX>5000100020</POSEX><RANG>0</RANG><MATKL>SERV00002</MATKL><MENGE>             0</MENGE><MENEE>VB</MENEE><PMENE>VB</PMENE><VPREI>8500</VPREI><PEINH>1</PEINH><NETWR>25500</NETWR><CURCY>BRL</CURCY><PREIS>8500</PREIS><AUSGB>0000</AUSGB><KTXT1>HONORÁRIOS 2</KTXT1><PERNR>00000000</PERNR><STELL>00000000</STELL><E1EDC02 SEGMENT=\"1\"><QUALF>001</QUALF><XLINE>5000100020</XLINE></E1EDC02><E1EDC02 SEGMENT=\"1\"><QUALF>003</QUALF><XLINE>0000000000</XLINE></E1EDC02><E1EDCT1 SEGMENT=\"1\"><TDID>ARB</TDID><E1EDCT2 SEGMENT=\"1\"><TDLINE>00001</TDLINE></E1EDCT2></E1EDCT1><E1EDCT1 SEGMENT=\"1\"><TDID>PACK</TDID><E1EDCT2 SEGMENT=\"1\"><TDLINE>0-0000000020</TDLINE></E1EDCT2></E1EDCT1></E1EDC01></E1EDP01>",
							"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><E1EDP01 SEGMENT=\"1\"><POSEX>00002</POSEX><ACTION>001</ACTION><PSTYP>9</PSTYP><MENGE>             1</MENGE><MENEE>EA</MENEE><BMNG2>1.000</BMNG2><PMENE>EA</PMENE><VPREI>0.00</VPREI><PEINH>1</PEINH><NETWR>0.00</NETWR><ANETW>76500.00</ANETW><MATKL>SERV00002</MATKL><BPUMN>1</BPUMN><BPUMZ>1</BPUMZ><WERKS>14</WERKS><E1EDP05 SEGMENT=\"1\"><ALCKZ>+</ALCKZ><KSCHL>PBXX</KSCHL><KOTXT>Preço bruto</KOTXT><BETRG>76500</BETRG><KRATE>76500</KRATE><UPRBS>       1</UPRBS><MEAUN>EA</MEAUN><KOEIN>BRL</KOEIN></E1EDP05><E1EDP20 SEGMENT=\"1\"><WMENG>1.000</WMENG><AMENG>0.000</AMENG><EDATU>20161217</EDATU></E1EDP20><E1EDP19 SEGMENT=\"1\"><QUALF>001</QUALF><IDTNR>000000000008376288</IDTNR><KTEXT>JBM - SECONDMENT</KTEXT></E1EDP19><E1EDP35 SEGMENT=\"1\"><CUSADD>NCM</CUSADD><CUSADD_BEZ>LC116-17.14</CUSADD_BEZ></E1EDP35><E1EDP35 SEGMENT=\"1\"><QUALZ>NOP</QUALZ><CUSADD>1</CUSADD><CUSADD_BEZ>Industrialização</CUSADD_BEZ></E1EDP35><E1EDPT1 SEGMENT=\"1\"><TDID>F01</TDID><TSSPRAS>P</TSSPRAS><TSSPRAS_ISO>PT</TSSPRAS_ISO><E1EDPT2 SEGMENT=\"1\"><TDFORMAT>*</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>2222111Escopo: RC específica para contratação de Secondments do escritório</TDLINE><TDFORMAT>*</TDFORMAT></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>2222111ASDFASFDASDFpara alocação de advogado e estagiário, para prestação</TDLINE></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>222211de serviços na CSN, referente a atendimento e auxílio junto ao Projeto</TDLINE></E1EDPT2><E1EDPT2 SEGMENT=\"1\"><TDLINE>22221de Auditoria de Valores/Depósitos.</TDLINE></E1EDPT2></E1EDPT1><E1EDPT1 SEGMENT=\"1\"><TDID>ARB</TDID><E1EDPT2 SEGMENT=\"1\"><TDLINE>true</TDLINE></E1EDPT2></E1EDPT1><E1EDC01 SEGMENT=\"1\"><SGTYP>002</SGTYP><POSEX>0000000000</POSEX><RANG>0</RANG><MENGE>             0</MENGE><CURCY>BRL</CURCY><AUSGB>0000</AUSGB><FRPOS>    1</FRPOS><PERNR>00000000</PERNR><STELL>00000000</STELL><E1EDC02 SEGMENT=\"1\"><QUALF>001</QUALF><XLINE>0000000000</XLINE></E1EDC02></E1EDC01><E1EDC01 SEGMENT=\"1\"><SGTYP>002</SGTYP><POSEX>5000100010</POSEX><RANG>0</RANG><MATKL>SERV00002</MATKL><MENGE>             0</MENGE><MENEE>VB</MENEE><PMENE>VB</PMENE><VPREI>8500</VPREI><PEINH>1</PEINH><NETWR>51000</NETWR><CURCY>BRL</CURCY><PREIS>8500</PREIS><AUSGB>0000</AUSGB><KTXT1>HONORÁRIOS</KTXT1><PERNR>00000000</PERNR><STELL>00000000</STELL><E1EDC02 SEGMENT=\"1\"><QUALF>001</QUALF><XLINE>5000100010</XLINE></E1EDC02><E1EDC02 SEGMENT=\"1\"><QUALF>003</QUALF><XLINE>0000000000</XLINE></E1EDC02><E1EDCT1 SEGMENT=\"1\"><TDID>ARB</TDID><E1EDCT2 SEGMENT=\"1\"><TDLINE>00001</TDLINE></E1EDCT2></E1EDCT1><E1EDCT1 SEGMENT=\"1\"><TDID>PACK</TDID><E1EDCT2 SEGMENT=\"1\"><TDLINE>0-0000000010</TDLINE></E1EDCT2></E1EDCT1></E1EDC01><E1EDC01 SEGMENT=\"1\"><SGTYP>002</SGTYP><POSEX>5000100020</POSEX><RANG>0</RANG><MATKL>SERV00002</MATKL><MENGE>             0</MENGE><MENEE>VB</MENEE><PMENE>VB</PMENE><VPREI>8500</VPREI><PEINH>1</PEINH><NETWR>25500</NETWR><CURCY>BRL</CURCY><PREIS>8500</PREIS><AUSGB>0000</AUSGB><KTXT1>HONORÁRIOS 2</KTXT1><PERNR>00000000</PERNR><STELL>00000000</STELL><E1EDC02 SEGMENT=\"1\"><QUALF>001</QUALF><XLINE>5000100020</XLINE></E1EDC02><E1EDC02 SEGMENT=\"1\"><QUALF>003</QUALF><XLINE>0000000000</XLINE></E1EDC02><E1EDCT1 SEGMENT=\"1\"><TDID>ARB</TDID><E1EDCT2 SEGMENT=\"1\"><TDLINE>00001</TDLINE></E1EDCT2></E1EDCT1><E1EDCT1 SEGMENT=\"1\"><TDID>PACK</TDID><E1EDCT2 SEGMENT=\"1\"><TDLINE>0-0000000020</TDLINE></E1EDCT2></E1EDCT1></E1EDC01></E1EDP01>"}; 
		String keyfield = "POSEX";
		String[] keyvaluelist = {"00002"};
		//String textTag = "E1EDPT1";
		String textId = "TDID";
		String textIdValue = "F01";
		String resultField = "TDLINE";
		
		try {
			StringBuffer sb = new StringBuffer();
			for (String xml : xmllist) {
				if(xml.trim().length()>0){
					DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document document = docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
					NodeList tagslist = document.getElementsByTagName(keyfield);

					for(int i = 0; i< tagslist.getLength();i++) {
						Node node = tagslist.item(i);
						String nodename = node.getFirstChild().getNodeValue();
						for (String keyvalue : keyvaluelist) {

							if(nodename.equals(keyvalue)){
								//find the text Tag
								Element itemNode = (Element)node.getParentNode();
								NodeList textList = itemNode.getElementsByTagName(textId);
								for (int j = 0; j < textList.getLength(); j++) {
									Node textNode = textList.item(j);
									String textName = textNode.getFirstChild().getNodeValue();
									if(textName.equals(textIdValue)){
										//add the content of the result field to the output
										//look from the parrent node
										Element parrentNode = (Element)textNode.getParentNode();
										NodeList resultList = parrentNode.getElementsByTagName(resultField);
										for(int k = 0; k< resultList.getLength();k++) {
											Node resultnode = resultList.item(k);
											sb.append(resultnode.getFirstChild().getNodeValue());
											if (resultList.getLength() > 1 & k < (resultList.getLength() -1)) {
												sb.append("\n");
											}
										}
										
									}
								}
							}
						}
					}
					//result.addValue( sb.toString());
					System.out.println(sb.toString());
					sb = new StringBuffer();
				}
			}
		} catch(Exception e){
			//throw new StreamTransformationException("Unable to parse xml", e);
			System.out.println("Erro");
		}		
	}

	public static void selectXMLArguments() {
		String[] xmllist = {"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><ShipNoticeItem itemType=\"item\" shipNoticeLineNumber=\"1\" lineNumber=\"4\" quantity=\"3\"><ItemID><SupplierPartID>Non Catalog Item</SupplierPartID><BuyerPartID>000000000008000113</BuyerPartID></ItemID><ShipNoticeItemDetail><UnitPrice><Money currency=\"BRL\">18.00</Money></UnitPrice><Description xml:lang=\"pt-BR\">AGENDA TELEFONICA TILIBRA</Description><UnitOfMeasure>CDA</UnitOfMeasure></ShipNoticeItemDetail><UnitOfMeasure>CDA</UnitOfMeasure><Packaging><Dimension type=\"length\" quantity=\"10\"><UnitOfMeasure/></Dimension><Dimension type=\"weight\" quantity=\"35\"><UnitOfMeasure/></Dimension><Dimension type=\"height\" quantity=\"30\"><UnitOfMeasure/></Dimension><Dimension type=\"width\" quantity=\"15\"><UnitOfMeasure/></Dimension><PackageID>                  </PackageID><OrderedQuantity quantity=\"14000.000\"><UnitOfMeasure>CDA</UnitOfMeasure></OrderedQuantity></Packaging><Hazard><Description xml:lang=\"pt-BR\">CLASSE3</Description><Classification domain=\"UNDG\">ONU3</Classification></Hazard></ShipNoticeItem>",
							"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><E1CUCFG SEGMENT=\"1\"><POSEX>00001</POSEX><KBNAME>teste do anexo.xlsx</KBNAME><CFGINFO>application/vnd.openxmlformats-officedocument.spreadsheetml.sheet</CFGINFO><CBASE_ID>FE2AF11543021ED6B79716D6C255C6A0</CBASE_ID><E1CUBLB SEGMENT=\"1\"><CONTENT>504B03041400060008000000210062EE9D685E01000090040000130008025B436F6E74656E745F54797065735D2E786D6C20A2040228A000020000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000AC94CB4EC3301045F748FC43E42D4ADCB2400835ED82C7122A513EC0C493C6AA635B9E6969FF9E89FB1042A1156A37B112CFDC7B32F1CD68B26E6DB68288C6BB520C</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>BBBE371E4D341AB2A98AF4AA5AC6906B2BBF7C5C7C7ABF288E8BF450FABA3615685F2D5B9E40812182D2D800506B8BB416AD326ECF7DC43F15A34CCBF0C220DDFB25E1131CC4DF1B64BA9E8F90644E18226D2CE0A5C79E444F39372A827EA7C8C9B838C04FED631C7C6EA6D107E40445F8FF14F611E9BAF3C04210C9C0</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>CADBDD3C8DEAC821F6E234AC8B12143B23B677AD8697FA7175072A2672964671ACE1C41176D5F5D5F699474A792876BD8F2AABB8A8A14BC9DF2346D3F144B110CF2E571A0913A51C86163D99815AC64D59DE62F8AE01D54253EDAD86B0B737A0EA93CF9B7FD796A6E90D3F88394CECD29915C8736267D9AE7CC86C21F5</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>A000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>00000000AC524D4BC43010BD0BFE8730779B761511D9742F22EC55EB0F08C9B429DB2621337EF4DF1B2ABA5D58D64B2F036F8679EFCDC776F7350EE20313F5C12BA88A12047A136CEF3B056FCDF3CD030862EDAD1E8247051312ECEAEBABED0B0E9A7313B93E92C82C9E1438E6F828251987A3A62244F4B9D286346ACE</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>080000002100BF8D460134020000880400000F000000786C2F776F726B626F6F6B2E786D6CAC54CB6EDB3010BC17E83F10BCDB7A588A1DC152103F8A06288A2075928B2F34B5B20853A44A52B58DA2FFDE9554B5697D49D15EC425B91AEECC2C39BF3955927C01638556290DC63E25A0B8CE85DAA7F471F36E34A3C43A</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>AB0B374628AF2FF2826FE07B41D053CEE68590F0D4CB4E585D7F64557B8AA44432EBD6B97090A7F40AA7FA08BF2D98A65E3442E26E1045A14FBDECA715F786E450B046BA0D9A30C063623C09C3B0CD4452B7D28151CCC1522B871AFE50FF5FF5EAB097A54677C8037C6E84016C8A56B66C8E5FC613B6B3F7CC95A43132</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>18075D069CDC07EBB2398E28AC48E9D720F26FA7FE7534F2D7937814CDAEC3D12C9A84A365B40AD7F174BD5A2FE26FFFB797D1DF64780EDA2A4B66DCC6307EC047E4018A05B3D8DB3D21AC138D18AAF686BFB2EF000000FFFF0300504B030414000600080000002100A896CE799E000000BB00000014000000786C2F73</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>B63D620A91C1F47961AD5D300BC7D742973F7B2BD15BF54AA26402D39A2DAAB7F89BB1C6FD170000FFFF0300504B0304140006000800000021008B826E58930600008E1A000013000000786C2F7468656D652F7468656D65312E786D6CEC59CF8B1B3714BE17FA3F0C7377FC6B666C2FF1067B6C67DBEC2621EBA4E4A8</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>2FB0194EE0D984A53112709B4ECBE3141D83DD98966B954A508E11495C27413198BD369990117686D2A4BBBD34DEA7709B082E1B4634DD97A6B1D14361C7875589E00B1ED2D43942B4EDC23863763CC47785EB50C4053C68BB15F5E796B72F96D156DE898A0D7DB57E03F597F7CB3B8C0F6B6ACC747AB01AD4F37C2FE8</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>74000009A44890C4118B199EA01164718828394889B34BA61124DE0C258C4373A5561954EAF05FFE3C75A53C82B630D27A4B5EC084AF35493E0E1FA56426DAEE8760D5D5202F9F7DFFF2D913E7E5B3C7270F9E9E3CF8E9E4E1C393073F66B68C8E3B2899EA1D5F7CFBD99F5F7FECFCF1E49B178FBEB0E3B98EFFF5874F</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>5989CD64186183E6758A1281A638C1C291CFD821C696D9DD26C4F0EB1E19A58CB389706E13A78B88D5254372602452D16987C41097858D2084DAF0CDDE2DA7CBA86DD63D7C6422E18540D4427E88A9E1C6CB682E506C33394431D51DBE8B446423B9BF48473AAECF05447A8A2973FA63CCB9ADCFB514E6AB05FD0A888B</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>EEFF5EBF37BDCBE7AFDA8550838617AB75B5768F372EDD2784D27DB1A07897ABD53B87F2341E40A3DA56A8BDE56A2B378BE032DF2818B8698A541F2765E22322A2FD08CD60895F551BD129CF4D4FB933631C56FEAA596D89F129DB6AFF308FF7D838DBB156AB72779A890747A268AFF8AB76D86D880C1D348A5DD8CABC</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>1ACD37116B2922A7B48126BA52D0C4396EBB41DD87D3B1119AB5DD09ECFCE1329E41EE70B9EE45740AC7672391662FFCEB28CB2CE5A2877894395C894EA606311138752889DBAE9CFE2A1B68A2344471ABD64010DE5A722D9095B78D1C04DD0C329E4CF048E861D75AA4A7B35B50F84C2BAC4F55F7D707CB9E6C0EE1DE</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>C6EB322C353B6F35A99DE38240F344B0C16FAB1A61F5C4EB567EE8773A6B658158AE2B55E2AB4F1FFAD709767007C4A307E7C0732AB80A257C7B48112CFAB293E44C36E015B92BF235225C39F394B4DD7B15BFE385353F2C559A7EBFE4D5BD4AA9E977EAA58EEFD7AB7DBF5AE9756BF7A1B08828AEFAD96797019C47D1</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>0000002100DE23F2D385020000B10500000D000000786C2F7374796C65732E786D6CA4545B6F9B30147E9FB4FF60F99D1A68C89208A8960B52A56E9AD44EDAAB0326B1EA0BB24D976CDA7FEF319084AAD336AD2FF89CC3F177BE73737A7390023D3163B956198EAE428C982A75C5D52EC35F1F8A6086917554555468C5</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>B502681F61F1A8F47755F85FDED87BE5A9FD819EA8004B84499E965A68831C54068875164525EB3D5654F0ADE1DEADA6928B636F8EBDA12BE6E02739A4E68DC4F3180E0B97B8106756B12700863C85EA386654010A1AE4876303E11534B287E9FCFEE2BD33F418C5C9E802E902E6E9569B0A06E7528F93294F05AB1D10</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>67AF2FFC49BB0E22C317F9CE772A9AFA18ECE0EE2C8C179CA8353CC33F37CB0FF3F5A6888359B89C05936B9604F364B90E92C96AB95E17F3300E57BF468BF68635EB9E833C85C55A5801CB6886648714EF2FB60C8F949E7E37A3407BCC7D1E4FC38F491406C575180593299D05B3E97512144914AFA793E526299211F7</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>B0C6F6A5B6E7F5EF7B0D258A942EB2006CB8FECE3DC7A67CBC5AC3CEE08346B7E38B2CE70C9CC246BB6EC77FFE38DCDD7316A2748D34E860C76F10F863F5FE5D79417F0C3D4064447061C7FB18874288A07AB032643880A32F2D7A2B234D7D27C2E04136E3226BC432CF37C24AEDF84428FC5B18D8B65AC11ED5C9828B</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>49403C2BBC1CCF6A8771B7BE7AD6402B4F267EC3CB67D05D1F49764DEE53084573DB4350943E0967CB75A22A3484A03BB39A8ED192D293D7F179D14DEC69F5225B2DD7DBFB05D5B31A423CE8C4E44C9D4244FBFB5FD5D8E1041BFBDCCB28ABD2E385D1E158919541A6A3B62C48E1FFCD5017A996D26424102898739597</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>DF05BF43C9B35D9A56E60C6D073AF6E44070A2F81692BBADDAFC218976FBF6A6ED562BF3C1C7E49CFBCB3997E4F3BDACA32FB0AED2AA406492A00814D7A252DB023DAF97F10C45CE332558AD1514E8000ECDCBCB8B9C1BCAB58547AB0D585F818B024939CA4D8176DE1B8AB1E33B90CC4D82430571A3AD643E1CED161B</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>00220AE1685FE5A4BC64F78BF512956942A631496372BB26194D6734B97E6B03FC9A6FC3F617F218E3DFC46C46B39B11F10428737CF641CA6F000000FFFF0300504B030414000600080000002100DE4116D98A0100001103000010000801646F6350726F70732F6170702E786D6C20A2040128A0000100000000000000</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009C92416FDB30</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>08242FCBF28B840341A8A1BE48A7403125AE7AFADFD03ADA810F1F76C7C4C05A7D4BC93B6B886FA9EF9CCD116343C5F78305AFE45C544CB705FB941D1D75A9E4BC555B6B3CAC395837C62328F93250B76086A56D8CCBA8554FAB1E2CC55CA0FBCB6BBB14C51F8330E054A237D999408C35D8A666AC7D42CAFA77CC8FD8</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>FF0300504B01022D001400060008000000210062EE9D685E010000900400001300000000000000000000000000000000005B436F6E74656E745F54797065735D2E786D6C504B01022D0014000600080000002100B5553023F40000004C0200000B00000000000000000000000000970300005F72656C732F2E72656C73</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>786D6C504B01022D0014000600080000002100A896CE799E000000BB0000001400000000000000000000000000500B0000786C2F736861726564537472696E67732E786D6C504B01022D00140006000800000021008B826E58930600008E1A00001300000000000000000000000000200C0000786C2F7468656D652F74</CONTENT></E1CUBLB><E1CUBLB SEGMENT=\"1\"><CONTENT>656574312E786D6C504B01022D00140006000800000021002042B4DF470100006D0200001100000000000000000000000000A8170000646F6350726F70732F636F72652E786D6C504B01022D0014000600080000002100DE4116D98A010000110300001000000000000000000000000000261A0000646F6350726F7073</CONTENT></E1CUBLB></E1CUCFG>"};
		String keyfield = "CBASE_ID";
		String[] keyvaluelist = {"FE2AF11543021ED6B79714213A29869D","FE2AF11543021ED6B79716D6C255C6A0"};
		String resultField = "CONTENT";
		try {
			StringBuffer sb = new StringBuffer();
			for (String xml : xmllist) {
				if(xml.trim().length()>0){
					String[] keyvalues = keyvaluelist;
					DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document document = docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
					NodeList tagslist = document.getElementsByTagName(keyfield);

					for(int i = 0; i< tagslist.getLength();i++) {
						Node node = tagslist.item(i);
						String nodename = node.getFirstChild().getNodeValue();
						for (String keyvalue : keyvalues) {

							if(nodename.equals(keyvalue)){
								//add the content of the result field to the output
								//look from the parrent node
								Element parrentNode = (Element)node.getParentNode();
								NodeList resultList = parrentNode.getElementsByTagName(resultField);

								for(int j = 0; j< resultList.getLength();j++) {
									Node resultnode = resultList.item(j);
									sb.append(resultnode.getFirstChild().getNodeValue());
								}
								//result.addValue( sb.toString());
		 						System.out.println(sb.toString());
								sb = new StringBuffer();
							}
						}
					}
				}
			}
		} catch(Exception e){
			//throw new StreamTransformationException("Unable to parse xml", e);
			System.out.println("Erro");
		}

	}	
	public static void selectXMLArgumentsByAttribute() {
		String[] xmllist = {"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><ShipNoticeItem itemType=\"item\" shipNoticeLineNumber=\"1\" lineNumber=\"4\" quantity=\"3\"><ItemID><SupplierPartID>Non Catalog Item</SupplierPartID><BuyerPartID>000000000008000113</BuyerPartID></ItemID><ShipNoticeItemDetail><UnitPrice><Money currency=\"BRL\">18.00</Money></UnitPrice><Description xml:lang=\"pt-BR\">AGENDA TELEFONICA TILIBRA</Description><UnitOfMeasure>CDA</UnitOfMeasure></ShipNoticeItemDetail><UnitOfMeasure>CDA</UnitOfMeasure><Packaging><Dimension type=\"length\" quantity=\"10\"><UnitOfMeasure/></Dimension><Dimension type=\"weight\" quantity=\"35\"><UnitOfMeasure/></Dimension><Dimension type=\"height\" quantity=\"30\"><UnitOfMeasure/></Dimension><Dimension type=\"width\" quantity=\"15\"><UnitOfMeasure/></Dimension><PackageID>                  </PackageID><OrderedQuantity quantity=\"14000.000\"><UnitOfMeasure>CDA</UnitOfMeasure></OrderedQuantity></Packaging><Hazard><Description xml:lang=\"pt-BR\">CLASSE3</Description><Classification domain=\"UNDG\">ONU3</Classification></Hazard></ShipNoticeItem>",
							"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><ShipNoticeItem itemType=\"item\" shipNoticeLineNumber=\"2\" lineNumber=\"3\" quantity=\"2\"><ItemID><SupplierPartID>Non Catalog Item</SupplierPartID><BuyerPartID>000000000008000113</BuyerPartID></ItemID><ShipNoticeItemDetail><UnitPrice><Money currency=\"BRL\">20.00</Money></UnitPrice><Description xml:lang=\"pt-BR\">AGENDA TELEFONICA TILIBRA</Description><UnitOfMeasure>CDA</UnitOfMeasure></ShipNoticeItemDetail><UnitOfMeasure>CDA</UnitOfMeasure><Packaging><Dimension type=\"length\" quantity=\"2\"><UnitOfMeasure/></Dimension><Dimension type=\"weight\" quantity=\"5\"><UnitOfMeasure/></Dimension><Dimension type=\"height\" quantity=\"4\"><UnitOfMeasure/></Dimension><Dimension type=\"width\" quantity=\"3\"><UnitOfMeasure/></Dimension><PackageID>                  </PackageID><OrderedQuantity quantity=\"17000.000\"><UnitOfMeasure>CDA</UnitOfMeasure></OrderedQuantity></Packaging></ShipNoticeItem>"};
		String keyFieldName = "ShipNoticeItem";
		String keyAttributeName = "shipNoticeLineNumber";
		String[] keyValueList = {"1","2"};
		String resultFieldName = "Hazard/Description";
		String resultFieldAttribute = "";
		try {
			StringBuffer sb = new StringBuffer();
			for (String xml : xmllist) {
				if(xml.trim().length()>0){
					String[] keyvalues = keyValueList;
					DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document document = docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
					NodeList tagslist = document.getElementsByTagName(keyFieldName);

					for(int i = 0; i< tagslist.getLength();i++) {
						Node node = tagslist.item(i);
						String nodename = "";
						if(keyAttributeName.isEmpty()) {
							nodename = node.getFirstChild().getNodeValue();
						} else {
							Element elAttrKeyValue = (Element)node;
							nodename = elAttrKeyValue.getAttribute(keyAttributeName);
						}
						for (String keyvalue : keyvalues) {

							if(nodename.equals(keyvalue)){
								//add the content of the result field to the output
								//look from the parrent node
								Element parrentNode = (Element)node.getParentNode();
								NodeList resultList = parrentNode.getElementsByTagName(resultFieldName);

								for(int j = 0; j< resultList.getLength();j++) {
									Node resultnode = resultList.item(j);
									sb.append(resultnode.getFirstChild().getNodeValue());
								}
								//result.addValue( sb.toString());
		 						System.out.println(sb.toString());
								sb = new StringBuffer();
							}
						}
					}
				}
			}
		} catch(Exception e){
			//throw new StreamTransformationException("Unable to parse xml", e);
			System.out.println("Erro");
		}

	}	

	public static String splitByValue(int idx, String param, String charSplit) {
		String[] values = param.split("[" + charSplit +"]");
		if (values.length > idx) {
			return values[idx];	
		} else {
			return "";
		}
	}

	public static String replaceSpecialChar(String subjectString) { 
		//subjectString = "!@#$%¨&*()teste ção ü{}^?/°.asdf0()¨//°";
		subjectString = Normalizer.normalize(subjectString, Normalizer.Form.NFD);
		String resultString = subjectString.replaceAll("[^\\x20-\\x7A]", "");
		resultString = resultString.replaceAll("[\\x21-\\x2C \\x2F \\x3A-\\x40 \\x5B-\\x5E \\x60]", "");
		return resultString;
	}

	public static void selectXML_XPath() {
		String[] xmllist = {"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Comments xml:lang=\"pt-BR\" type=\"ReasonForShipment\">10:05</Comments>",
							"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Comments xml:lang=\"pt-BR\" type=\"CommentsToBuyer\">Com345</Comments>"};
		//String xpathExpression = "/ShipNoticeItem[@shipNoticeLineNumber=#]/Hazard/Description";
		//String xpathExpression = "/ShipNoticeItem[@shipNoticeLineNumber=#]/Hazard/Classification[@domain='UNDG']";
		String xpathExpression = "/Comments[@type='ReasonForShipment']";
		String[] xpathValues = {""};
		String returnBlankLines = "FALSE";
		XPath xPath =  XPathFactory.newInstance().newXPath();
		try {
			for (int i = 0; i < xmllist.length; i++) {
				
				String xml = xmllist[i];
				String xpathExpressionFilter = "";
				if (xmllist.length == xpathValues.length) {
					xpathExpressionFilter = xpathExpression.replace("#", xpathValues[i]); 
				} else {
					xpathExpressionFilter = xpathExpression.replace("#", xpathValues[0]);
				}
				
				DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document document = docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
				NodeList nodeList = (NodeList) xPath.compile(xpathExpressionFilter).evaluate(document, XPathConstants.NODESET);
				if (nodeList.getLength() > 0 ) {
					for (int j = 0; j < nodeList.getLength(); j++) {
						if (nodeList.item(j).getFirstChild() !=null ) {
							System.out.println(nodeList.item(j).getFirstChild().getNodeValue());
							//result.addValue( nodeList.item(j).getFirstChild().getNodeValue() );
						} else {
							if (returnBlankLines.toUpperCase().equals("TRUE")){
								//result.addValue(" ");
								System.out.println("--------");						
							}
						}							
					}
				} else {
					if (returnBlankLines.toUpperCase().equals("TRUE")){
						//result.addValue(" ");
						System.out.println("----------");						
					}
				}
			}
		} catch(Exception e){
			//AbstractTrace trace = getTrace();
			//trace.addWarning("Unable to parse xml");
			if (returnBlankLines.toUpperCase().equals("TRUE")){
				//result.addValue(" ");
				System.out.println("----------");						
			}
			e.printStackTrace();
		}
	}
	
}

