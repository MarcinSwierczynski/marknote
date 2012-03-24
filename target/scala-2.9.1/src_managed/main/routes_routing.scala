// @SOURCE:/Users/marcin/Scala_projects/marknote/conf/routes
// @HASH:89bb7611df5f836977103100a1d74aece00289a0
// @DATE:Sat Mar 24 15:03:45 CET 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart("/"))))
                    

// @LINE:9
val controllers_Notes_notes1 = Route("GET", PathPattern(List(StaticPart("/notes"))))
                    

// @LINE:10
val controllers_Notes_newNote2 = Route("POST", PathPattern(List(StaticPart("/notes"))))
                    

// @LINE:11
val controllers_Notes_deleteNote3 = Route("POST", PathPattern(List(StaticPart("/notes/"),DynamicPart("id", """[^/]+"""),StaticPart("/delete"))))
                    

// @LINE:14
val controllers_Assets_at4 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/""","""controllers.Application.index"""),("""GET""","""/notes""","""controllers.Notes.notes"""),("""POST""","""/notes""","""controllers.Notes.newNote"""),("""POST""","""/notes/$id<[^/]+>/delete""","""controllers.Notes.deleteNote(id:Long)"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(_root_.controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil))
   }
}
                    

// @LINE:9
case controllers_Notes_notes1(params) => {
   call { 
        invokeHandler(_root_.controllers.Notes.notes, HandlerDef(this, "controllers.Notes", "notes", Nil))
   }
}
                    

// @LINE:10
case controllers_Notes_newNote2(params) => {
   call { 
        invokeHandler(_root_.controllers.Notes.newNote, HandlerDef(this, "controllers.Notes", "newNote", Nil))
   }
}
                    

// @LINE:11
case controllers_Notes_deleteNote3(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Notes.deleteNote(id), HandlerDef(this, "controllers.Notes", "deleteNote", Seq(classOf[Long])))
   }
}
                    

// @LINE:14
case controllers_Assets_at4(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                