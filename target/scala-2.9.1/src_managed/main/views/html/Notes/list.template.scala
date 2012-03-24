
package views.html.Notes

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[List[Note],Form[String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(notes: List[Note], noteForm: Form[String]):play.api.templates.Html = {
        _display_ {import helper._


Seq(format.raw/*1.45*/("""

"""),format.raw/*4.1*/("""
"""),_display_(Seq(/*5.2*/main("Notes list")/*5.20*/ {_display_(Seq(format.raw/*5.22*/("""

	<script>
		$(function() """),format.raw("""{"""),format.raw/*8.17*/("""
			$("#notes").accordion("""),format.raw("""{"""),format.raw/*9.27*/(""" collapsible: true, active: false """),format.raw("""}"""),format.raw/*9.62*/(""");
		"""),format.raw("""}"""),format.raw/*10.4*/(""");
	</script>

    <h1>"""),_display_(Seq(/*13.10*/notes/*13.15*/.size)),format.raw/*13.20*/(""" note(s)</h1>

	<div id="notes">
        """),_display_(Seq(/*16.10*/notes/*16.15*/.map/*16.19*/ { note =>_display_(Seq(format.raw/*16.29*/("""
			<h3>"""),_display_(Seq(/*17.9*/note/*17.13*/.title)),format.raw/*17.19*/("""</h3>
			<div><p>"""),_display_(Seq(/*18.13*/note/*18.17*/.convertedContent)),format.raw/*18.34*/("""</p></div>
        """)))})),format.raw/*19.10*/("""
	</div>

    <h2>Add a new note</h2>

    """),_display_(Seq(/*24.6*/form(routes.Notes.newNote)/*24.32*/ {_display_(Seq(format.raw/*24.34*/("""

        """),_display_(Seq(/*26.10*/textarea(noteForm("content")))),format.raw/*26.39*/("""

        <input type="submit" value="Create">

    """)))})),format.raw/*30.6*/("""

""")))})),format.raw/*32.2*/("""
"""))}
    }
    
    def render(notes:List[Note],noteForm:Form[String]) = apply(notes,noteForm)
    
    def f:((List[Note],Form[String]) => play.api.templates.Html) = (notes,noteForm) => apply(notes,noteForm)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Mar 24 15:03:46 CET 2012
                    SOURCE: /Users/marcin/Scala_projects/marknote/app/views/Notes/list.scala.html
                    HASH: a474db74b0cbf138bfc674454169f289375ba322
                    MATRIX: 527->1|658->44|686->63|717->65|743->83|777->85|851->113|924->140|1005->175|1057->181|1112->205|1126->210|1153->215|1226->257|1240->262|1253->266|1296->276|1335->285|1348->289|1376->295|1425->313|1438->317|1477->334|1529->354|1603->398|1638->424|1673->426|1715->437|1766->466|1850->519|1884->522
                    LINES: 19->1|23->1|25->4|26->5|26->5|26->5|29->8|30->9|30->9|31->10|34->13|34->13|34->13|37->16|37->16|37->16|37->16|38->17|38->17|38->17|39->18|39->18|39->18|40->19|45->24|45->24|45->24|47->26|47->26|51->30|53->32
                    -- GENERATED --
                */
            