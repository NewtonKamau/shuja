import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class  App{
  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";
  get("/", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/index.vtl" );
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/squads", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("squads", Squad.all());
        model.put("template", "templates/squads.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/squads/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/squad-form.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/squads", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String squadName = request.queryParams("squadName");
        String cause = request.queryParams("cause");
        Squad userSquad = new Squad(squadName, cause);
        model.put("template", "templates/squad-success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/squads/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Squad userSquad = Squad.find(Integer.parseInt(request.params(":id")));
        model.put("squad", userSquad);
        model.put("template", "templates/squad.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/squads/:id/heroes/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
        model.put("squad", squad);
        model.put("template", "templates/squad-heroes-form.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/heroes", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Squad squad = Squad.find(Integer.parseInt(request.queryParams("squadId")));
        String name = request.queryParams("name");
        int age = Integer.parseInt(request.queryParams("age"));
        String power = request.queryParams("power");
        String weakness = request.queryParams("weakness");
        Hero userHero = new Hero(name, age, power, weakness);
        squad.addHero(userHero);
        squad.squadStats(userHero);
        model.put("template", "templates/hero-success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/heroes", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       model.put("heroes", Hero.all());
       model.put("template", "templates/heroes.vtl");
       return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/heroes/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Hero userHero = Hero.find(Integer.parseInt(request.params(":id")));
        model.put("heroes", userHero);
        model.put("template", "templates/hero.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());



  }
}
