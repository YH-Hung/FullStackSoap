namespace FakeGuiBff.Controllers

open System.IO
open Microsoft.AspNetCore.Http
open Microsoft.AspNetCore.Mvc
open FakeGuiBff.Models
open Microsoft.Extensions.Logging

[<ApiController>]
[<Route("[controller]")>]
type ProductDataController(logger : ILogger<ProductDataController>) =
    inherit ControllerBase()
    
    [<HttpPost>]
    member _.Post(file: IFormFile) =
        use sr = new StreamReader(file.OpenReadStream())
        while sr.Peek() > 0 do
            logger.LogInformation("Read row: {RowContent}", sr.ReadLine())
        
        [|
            { IsSuccess = true; Message = "Row 1 is success" }
            { IsSuccess = false; Message = "Row 2 is fail" }
        |]