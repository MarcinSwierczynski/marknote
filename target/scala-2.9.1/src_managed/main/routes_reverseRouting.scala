// @SOURCE:/Users/marcin/Scala_projects/marknote/conf/routes
// @HASH:89bb7611df5f836977103100a1d74aece00289a0
// @DATE:Sat Mar 24 15:03:45 CET 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index() = {
   Call("GET", "/")
}
                                                        

                      
    
}
                            

// @LINE:14
class ReverseAssets {
    


 
// @LINE:14
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseNotes {
    


 
// @LINE:9
def notes() = {
   Call("GET", "/notes")
}
                                                        
 
// @LINE:10
def newNote() = {
   Call("POST", "/notes")
}
                                                        
 
// @LINE:11
def deleteNote(id:Long) = {
   Call("POST", "/notes/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                                                        

                      
    
}
                            
}
                    


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"/"})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:14
class ReverseAssets {
    


 
// @LINE:14
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseNotes {
    


 
// @LINE:9
def notes = JavascriptReverseRoute(
   "controllers.Notes.notes",
   """
      function() {
      return _wA({method:"GET", url:"/notes"})
      }
   """
)
                                                        
 
// @LINE:10
def newNote = JavascriptReverseRoute(
   "controllers.Notes.newNote",
   """
      function() {
      return _wA({method:"POST", url:"/notes"})
      }
   """
)
                                                        
 
// @LINE:11
def deleteNote = JavascriptReverseRoute(
   "controllers.Notes.deleteNote",
   """
      function(id) {
      return _wA({method:"POST", url:"/notes/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index() = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq())
)
                              

                      
    
}
                            

// @LINE:14
class ReverseAssets {
    


 
// @LINE:14
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseNotes {
    


 
// @LINE:9
def notes() = new play.api.mvc.HandlerRef(
   controllers.Notes.notes(), HandlerDef(this, "controllers.Notes", "notes", Seq())
)
                              
 
// @LINE:10
def newNote() = new play.api.mvc.HandlerRef(
   controllers.Notes.newNote(), HandlerDef(this, "controllers.Notes", "newNote", Seq())
)
                              
 
// @LINE:11
def deleteNote(id:Long) = new play.api.mvc.HandlerRef(
   controllers.Notes.deleteNote(id), HandlerDef(this, "controllers.Notes", "deleteNote", Seq(classOf[Long]))
)
                              

                      
    
}
                            
}
                    
                