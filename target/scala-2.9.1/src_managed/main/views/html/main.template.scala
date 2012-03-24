
package views.html

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
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq(/*7.17*/title)),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq(/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq(/*9.54*/routes/*9.60*/.Assets.at("stylesheets/jquery-themes/smoothness/jquery-ui-1.8.17.custom.css"))),format.raw/*9.138*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq(/*10.59*/routes/*10.65*/.Assets.at("images/favicon.png"))),format.raw/*10.97*/("""">
        <script src=""""),_display_(Seq(/*11.23*/routes/*11.29*/.Assets.at("javascripts/jquery-1.7.1.min.js"))),format.raw/*11.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq(/*12.23*/routes/*12.29*/.Assets.at("javascripts/jquery-ui-1.8.17.custom.min.js"))),format.raw/*12.85*/("""" type="text/javascript"></script>
    </head>
    <body>
        """),_display_(Seq(/*15.10*/content)),format.raw/*15.17*/("""
    </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html) = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.Html) = (title) => (content) => apply(title)(content)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Mar 24 15:03:46 CET 2012
                    SOURCE: /Users/marcin/Scala_projects/marknote/app/views/main.scala.html
                    HASH: 0df8b7f59d42364f3ffdbe9ebc2ee2648f2591c2
                    MATRIX: 509->1|611->31|694->84|720->89|812->151|826->157|881->191|967->247|981->253|1081->331|1173->392|1188->398|1242->430|1298->455|1313->461|1380->506|1468->563|1483->569|1561->625|1659->692|1688->699
                    LINES: 19->1|22->1|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|32->11|32->11|32->11|33->12|33->12|33->12|36->15|36->15
                    -- GENERATED --
                */
            