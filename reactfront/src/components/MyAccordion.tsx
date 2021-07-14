import React from 'react';
import { Theme, createStyles, makeStyles } from '@material-ui/core/styles';
import Accordion from '@material-ui/core/Accordion';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import { CommandForm } from './CommandForm';

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            width: '100%',
            margin:"5px"
            
        },
        heading: {
            fontSize: theme.typography.pxToRem(15),
            fontWeight: theme.typography.fontWeightRegular,
        },
        time:{
          textAlign:"center",
        },
        expand:{
          backgroundColor:"lavender",
          borderRadius:"5px"
        }
    }),
);

interface FormProps {
  stockname:string,
}

export default  function MyAccordion(params:FormProps) {
    const classes = useStyles();

    return (
        <div className={classes.root}>
                         <Accordion>
                          <AccordionSummary className={classes.expand}  expandIcon={<ExpandMoreIcon />} aria-controls="panel1a-content" id="panel1a-header">
                            <Typography className={classes.heading}> <strong>Exchange {params.stockname} Stock</strong> </Typography>
                          </AccordionSummary>
                          <AccordionDetails>
                          <div className="row">
                              <div className="col-lg-10 col-xl-14">
                                  <div className="card shadow mb-4">
                                    <CommandForm stockname={params.stockname}/>
                           </div>
                           </div>
                           </div>
                          
                          </AccordionDetails>
                        </Accordion>
             
    </div>

    )
}